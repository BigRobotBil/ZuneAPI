package me.hyname.storage;

import me.hyname.storage.impl.CosmosStorage;
import me.hyname.storage.impl.MongoStorage;
import me.hyname.storage.impl.SqlStorage;

public enum StorageType {
    MONGODB,
    SQL,
    COSMOS,
    OTHER;
}
