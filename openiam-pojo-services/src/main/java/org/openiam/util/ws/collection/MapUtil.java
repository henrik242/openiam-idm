/*
 * Created on Aug 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.openiam.util.ws.collection;

import java.util.*;

/**
 * Utility class to move between Maps and Arrays of objects.
 */
public class MapUtil {


	public MapUtil() {
	}
	
	public static Object get(String key, MapItem[] items) {
		if (items == null)
			return null;
		
		int size = items.length;
		for (int i = 0; i < size; i++) {
			MapItem item = (MapItem) items[i];
			if (item.getKey().equals(key))
				return item.getItem();
		}
		return null;
	}
	
	public static MapItem[] remove(String key, MapItem[] items) {
		if (items == null)
			return null;
		MapItem[] newItems = new MapItem[items.length];
		int j = 0;
		int size = items.length;
		for (int i = 0; i < size; i++) {
			MapItem item = (MapItem) items[i];
			if (item.getKey().equals(key))
				newItems[j++] = items[i];
		}
		return newItems;
	}
	
	public static MapItem[] clear(MapItem[] items) {
		if (items == null)
			return null;
		MapItem[] newItems = new MapItem[items.length];
		return newItems;
	}
	
	
	public static Map getMap(MapItem[] items) {
		if (items == null)
			return null;
		Map m = new HashMap();
		int size = items.length;
		for (int i = 0; i < size; i++) {
			MapItem item = (MapItem) items[i];
			m.put(item.getKey(), item.getItem());
		}
		return m;		
	}

	public static MapItem[] addMapItem(MapItem item, MapItem[] itemAry) {
		if (itemAry == null)
			return null;
		int size = itemAry.length;
		itemAry[size] = item;
		return itemAry;
				
	}

	public static MapItem[] updateMapItem(MapItem item, MapItem[] itemAry) {
		if (itemAry == null)
			return null;
		
		int size = itemAry.length;
		for (int i = 0; i < size; i++) {
			MapItem mItem = (MapItem) itemAry[i];
			if (mItem.getKey().equals(item.getKey())) {
				itemAry[i] = item;
			}
		}
		return itemAry;
				
	}
	
	public static MapItem[] getArray(Map m) {
		if (m == null)
			return null;
		Set keys = m.keySet();
		Iterator iKeys = keys.iterator();
		MapItem[] items = new MapItem[m.size()];
		for (int i = 0; i < m.size(); i++) {
			MapItem item = new MapItem();
			String key = (String)iKeys.next();
			item.setKey(key);
			item.setItem(m.get(key));
			items[i] = item;
		}
		return items;
	}
}
