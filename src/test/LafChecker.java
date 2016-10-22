package test;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class LafChecker {
	private static class EntryInfo {
		public Object key;
		public Object value;
		
		public EntryInfo(Object key, Object value) {
			super();
			this.key = key;
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof EntryInfo) {
				EntryInfo that = (EntryInfo) obj;
				return this.key.equals(that.key) && (this.value.equals(that.value)); 
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return (key == null ? 0 : key.hashCode()) + 31 * (value == null ? 0 : value.hashCode());
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Set<EntryInfo> defaultSet = new HashSet<EntryInfo>();
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		UIDefaults defaultUid = UIManager.getLookAndFeelDefaults();
		Enumeration<Object> defaultKeys = defaultUid.keys();
		while (defaultKeys.hasMoreElements()) {
			Object key = defaultKeys.nextElement();
			defaultSet.add(new EntryInfo(key, defaultUid.get(key)));
		}
		
		Set<EntryInfo> metalSet = new HashSet<EntryInfo>();
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		UIDefaults metalUid = UIManager.getLookAndFeelDefaults();
		Enumeration<Object> metalKeys = metalUid.keys();
		while (metalKeys.hasMoreElements()) {
			Object key = metalKeys.nextElement();
			metalSet.add(new EntryInfo(key, metalUid.get(key)));
		}
		
		for (EntryInfo entryInfo : defaultSet) {
			if (metalSet.contains(entryInfo)) {
				System.out.println(entryInfo.key + " : " + entryInfo.value);
				
			}
		}
//			
//			if (key instanceof String) {
////				if (((String) key).indexOf("render") >= 0) {
//				Object value = uid.get(key);
//				System.out.println(key + " : " + value);
////				}
//			}
//		}
	}

}
