/*
 * Copyright (c) 2005-2017 Substance Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of Substance Kirill Grouchnikov nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.substance.internal.utils;

import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.JComponent;

import org.pushingpixels.substance.api.SubstanceCortex;
import org.pushingpixels.substance.api.SubstanceSlices;

/**
 * Various utility functions for title panes. This class is <b>for internal use only</b>.
 * 
 * @author Kirill Grouchnikov
 */
public class SubstanceTitlePaneUtilities {
    /**
     * Client property to mark every child to be either leading or trailing. The value must be one
     * of {@link ExtraComponentKind}.
     * 
     * @see #markTitlePaneExtraComponent(JComponent, ExtraComponentKind)
     * @see #getTitleTextRectangle()
     */
    public static final String EXTRA_COMPONENT_KIND = "substancelaf.internal.titlePane.extraComponentKind";

    /** Enumerates the types of children components of title panes. */
    public static enum ExtraComponentKind {
        /** Leading child components (left on LTR and right on RTL). */
        LEADING,

        /** Trailing child components (right on LTR and left on RTL). */
        TRAILING,

        /** Child components anchored to the title. */
        WITH_TITLE
    }

    public static ExtraComponentKind getTitlePaneControlButtonKind() {
        SubstanceSlices.Gravity controlButtonGroupGravity = SubstanceCortex.GlobalScope
                .getTitleControlButtonGroupGravity();
        switch (controlButtonGroupGravity) {
            case LEADING:
                return ExtraComponentKind.LEADING;
            case TRAILING:
            case SWING_DEFAULT:
                return ExtraComponentKind.TRAILING;
            case PLATFORM:
            default: {
                SubstanceCoreUtilities.Platform platform = SubstanceCoreUtilities.getPlatform();
                switch (platform) {
                    case MACOS:
                        return ExtraComponentKind.LEADING;
                    default:
                        return ExtraComponentKind.TRAILING;
                }
            }
        }
    }

    public static boolean areTitlePaneControlButtonsOnRight(Component component) {
        boolean isLtr = component.getComponentOrientation().isLeftToRight();
        SubstanceSlices.Gravity controlButtonGroupGravity = SubstanceCortex.GlobalScope
                .getTitleControlButtonGroupGravity();
        switch (controlButtonGroupGravity) {
            case LEADING:
                return isLtr ? false : true;
            case TRAILING:
            case SWING_DEFAULT:
                return isLtr ? true : false;
            case PLATFORM:
            default: {
                SubstanceCoreUtilities.Platform platform = SubstanceCoreUtilities.getPlatform();
                switch (platform) {
                    case MACOS:
                        return isLtr ? false : true;
                    default:
                        return isLtr ? true : false;
                }
            }
        }
    }

    public static SubstanceSlices.Gravity getTitlePaneTextGravity() {
        SubstanceSlices.Gravity titleTextGravity = SubstanceCortex.GlobalScope
                .getTitleTextGravity();
        switch (titleTextGravity) {
            case LEADING:
            case TRAILING:
            case CENTERED:
                return titleTextGravity;
            case SWING_DEFAULT:
                return SubstanceSlices.Gravity.LEADING;
            case PLATFORM:
            default: {
                SubstanceCoreUtilities.Platform platform = SubstanceCoreUtilities.getPlatform();
                switch (platform) {
                    case WINDOWS:
                        return SubstanceSlices.Gravity.LEADING;
                    default:
                        return SubstanceSlices.Gravity.CENTERED;
                }
            }
        }
    }

    public static SubstanceSlices.TitleIconGravity getTitlePaneIconGravity() {
        SubstanceSlices.TitleIconGravity iconGravity = SubstanceCortex.GlobalScope
                .getTitleIconGravity();
        switch (iconGravity) {
            case NONE:
            case OPPOSITE_CONTROL_BUTTONS:
            case NEXT_TO_TITLE:
                return iconGravity;
            case SWING_DEFAULT:
                return SubstanceSlices.TitleIconGravity.OPPOSITE_CONTROL_BUTTONS;
            case PLATFORM:
            default: {
                SubstanceCoreUtilities.Platform platform = SubstanceCoreUtilities.getPlatform();
                switch (platform) {
                    case MACOS:
                        return SubstanceSlices.TitleIconGravity.NEXT_TO_TITLE;
                    case GNOME:
                        return SubstanceSlices.TitleIconGravity.NONE;
                    default:
                        return SubstanceSlices.TitleIconGravity.OPPOSITE_CONTROL_BUTTONS;
                }
            }
        }
    }

    /**
     * Marks the specified child component with the specified extra component kind.
     * 
     * @param comp
     *            Child component.
     * @param kind
     *            Extra kind.
     * @see #EXTRA_COMPONENT_KIND
     */
    public static void markTitlePaneExtraComponent(JComponent comp, ExtraComponentKind kind) {
        comp.putClientProperty(EXTRA_COMPONENT_KIND, kind);
    }

    /**
     * Computes the rectangle of the title text in the specified title pane. This method looks at
     * all the children components of the title pane, grouping them by leading and trailing (based
     * on {@link #EXTRA_COMPONENT_KIND} client property). The title text rectangle is the space
     * between the leading group and the trailing group.
     * 
     * @return Rectangle of the title text.
     * @throws IllegalStateException
     *             If at least one child component of this title pane is not marked with the
     *             {@link #EXTRA_COMPONENT_KIND} client property.
     * @see #markTitlePaneExtraComponent(JComponent, ExtraComponentKind)
     * @see #EXTRA_COMPONENT_KIND
     */
    public static Rectangle getTitlePaneTextRectangle(JComponent titlePane,
            Component rootComponent) {
        boolean leftToRight = rootComponent.getComponentOrientation().isLeftToRight();

        if (leftToRight) {
            int maxLeadingX = 0;
            int minTrailingX = titlePane.getWidth();
            int extraWidthTaken = 0;

            for (int i = 0; i < titlePane.getComponentCount(); i++) {
                Component child = titlePane.getComponent(i);
                if (!child.isVisible())
                    continue;
                if (child instanceof JComponent) {
                    ExtraComponentKind kind = (ExtraComponentKind) ((JComponent) child)
                            .getClientProperty(EXTRA_COMPONENT_KIND);
                    if (kind == null) {
                        throw new IllegalStateException(
                                "Title pane child " + child.getClass().getName()
                                        + " is not marked as leading or trailing");
                    }
                    switch (kind) {
                        case LEADING:
                            int childRight = child.getX() + child.getWidth();
                            if (childRight > maxLeadingX)
                                maxLeadingX = childRight;
                            break;
                        case TRAILING:
                            int childLeft = child.getX();
                            if (childLeft < minTrailingX)
                                minTrailingX = childLeft;
                            break;
                        default:
                            extraWidthTaken += child.getWidth();
                    }
                }
            }

            int start = maxLeadingX + 5 + extraWidthTaken;
            int end = minTrailingX - 5;
            return new Rectangle(start, 0, end - start, titlePane.getHeight());
        } else {
            int minLeadingX = titlePane.getWidth();
            int maxTrailingX = 0;
            int extraWidthTaken = 0;

            for (int i = 0; i < titlePane.getComponentCount(); i++) {
                Component child = titlePane.getComponent(i);
                if (!child.isVisible())
                    continue;
                if (child instanceof JComponent) {
                    ExtraComponentKind kind = (ExtraComponentKind) ((JComponent) child)
                            .getClientProperty(EXTRA_COMPONENT_KIND);
                    if (kind == null) {
                        throw new IllegalStateException(
                                "Title pane child " + child.getClass().getName()
                                        + " is not marked as leading or trailing");
                    }
                    switch (kind) {
                        case LEADING:
                            int childLeft = child.getX();
                            if (childLeft < minLeadingX)
                                minLeadingX = childLeft;
                            break;
                        case TRAILING:
                            int childRight = child.getX() + child.getWidth();
                            if (childRight > maxTrailingX)
                                maxTrailingX = childRight;
                            break;
                        default:
                            extraWidthTaken += child.getWidth();
                    }
                }
            }

            int start = maxTrailingX + 5;
            int end = minLeadingX - 5 - extraWidthTaken;
            return new Rectangle(start, 0, end - start, titlePane.getHeight());
        }
    }

}
