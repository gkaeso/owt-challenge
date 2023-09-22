create sequence seq_contact_manager_id minvalue 1 maxvalue 99999999 increment by 1 no cycle;

create table contact_manager
(
    id                 int8 generated by default as identity,
    key_id             varchar(64) not null,
    created_date       timestamp,
    last_modified_date timestamp,
    contact_id         int8        not null,
    manager_id         varchar     not null,
    primary key (id)
);

create index idx_contact_manager_id on contact_manager (id);

create index idx_contact_manager_key_id on contact_manager (key_id);

alter table contact_manager
    add constraint fk_contact_manager_contact_id foreign key (contact_id) references contact;

alter table contact_manager
    add constraint uq_contact_manager_key_id unique (key_id);

alter table contact_manager
    add constraint uq_contact_manager_contact_id_manager_id unique (contact_id, manager_id);