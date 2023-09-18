package com.owt.api.core;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity
{
    public abstract long getId();

    @Getter
    @Type(type = "uuid-char")
    @Column(nullable = false, updatable = false, unique = true, columnDefinition = "varchar(64)")
    protected UUID keyId;

    @CreatedDate
    @CreationTimestamp
    private Instant createdDate;

    @LastModifiedDate
    @UpdateTimestamp
    private Instant lastModifiedDate;

    @PrePersist
    private void assignKeyId()
    {
        if (Objects.isNull(keyId))
        {
            keyId = UUID.randomUUID();
        }
    }

    @Override
    public int hashCode()
    {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        if (this == o)
            return true;
        if (o instanceof BaseEntity object)
            return getId() == object.getId();
        return false;
    }
}
