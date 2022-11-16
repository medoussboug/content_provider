package com.example.crud1_content_provider.data;

import android.provider.BaseColumns;

public final class PersonContract {
    private PersonContract() {}

    public static class PersonContractEntry implements BaseColumns {
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_MAJOR = "major";
    }
}
