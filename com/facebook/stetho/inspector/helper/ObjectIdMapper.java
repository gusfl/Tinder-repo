package com.facebook.stetho.inspector.helper;

import android.util.SparseArray;
import java.util.IdentityHashMap;
import java.util.Map;

public class ObjectIdMapper {
    private SparseArray<Object> mIdToObjectMap;
    private int mNextId;
    private final Map<Object, Integer> mObjectToIdMap;
    protected final Object mSync;

    public ObjectIdMapper() {
        this.mSync = new Object();
        this.mNextId = 1;
        this.mObjectToIdMap = new IdentityHashMap();
        this.mIdToObjectMap = new SparseArray();
    }

    public void clear() {
        SparseArray sparseArray;
        synchronized (this.mSync) {
            sparseArray = this.mIdToObjectMap;
            this.mObjectToIdMap.clear();
            this.mIdToObjectMap = new SparseArray();
        }
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            onUnmapped(sparseArray.valueAt(i), sparseArray.keyAt(i));
        }
    }

    public boolean containsId(int i) {
        boolean z;
        synchronized (this.mSync) {
            z = this.mIdToObjectMap.get(i) != null;
        }
        return z;
    }

    public boolean containsObject(Object obj) {
        boolean containsKey;
        synchronized (this.mSync) {
            containsKey = this.mObjectToIdMap.containsKey(obj);
        }
        return containsKey;
    }

    public Object getObjectForId(int i) {
        Object obj;
        synchronized (this.mSync) {
            obj = this.mIdToObjectMap.get(i);
        }
        return obj;
    }

    public Integer getIdForObject(Object obj) {
        Integer num;
        synchronized (this.mSync) {
            num = (Integer) this.mObjectToIdMap.get(obj);
        }
        return num;
    }

    public int putObject(Object obj) {
        synchronized (this.mSync) {
            Integer num = (Integer) this.mObjectToIdMap.get(obj);
            if (num != null) {
                int intValue = num.intValue();
                return intValue;
            }
            intValue = this.mNextId;
            this.mNextId = intValue + 1;
            num = Integer.valueOf(intValue);
            this.mObjectToIdMap.put(obj, num);
            this.mIdToObjectMap.put(num.intValue(), obj);
            onMapped(obj, num.intValue());
            return num.intValue();
        }
    }

    public Object removeObjectById(int i) {
        Object obj;
        synchronized (this.mSync) {
            obj = this.mIdToObjectMap.get(i);
            if (obj == null) {
                obj = null;
            } else {
                this.mIdToObjectMap.remove(i);
                this.mObjectToIdMap.remove(obj);
                onUnmapped(obj, i);
            }
        }
        return obj;
    }

    public Integer removeObject(Object obj) {
        Integer num;
        synchronized (this.mSync) {
            num = (Integer) this.mObjectToIdMap.remove(obj);
            if (num == null) {
                num = null;
            } else {
                this.mIdToObjectMap.remove(num.intValue());
                onUnmapped(obj, num.intValue());
            }
        }
        return num;
    }

    public int size() {
        int size;
        synchronized (this.mSync) {
            size = this.mObjectToIdMap.size();
        }
        return size;
    }

    protected void onMapped(Object obj, int i) {
    }

    protected void onUnmapped(Object obj, int i) {
    }
}
