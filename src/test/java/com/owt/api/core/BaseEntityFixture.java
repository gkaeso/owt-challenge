package com.owt.api.core;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class BaseEntityFixture
{
    private static long generator = 1;

    public static <T extends BaseEntity> T fillBase(T baseEntity)
    {
        return fillBase(generator++, baseEntity);
    }

    private static <T extends BaseEntity> T fillBase(long id, T baseEntity)
    {
        return fillBase(id, randomUUID(), baseEntity);
    }

    private static <T extends BaseEntity> T fillBase(long id, UUID uuid, T baseEntity)
    {
        setField(baseEntity, "id", id);
        setField(baseEntity, "keyId", uuid);
        return baseEntity;
    }
}