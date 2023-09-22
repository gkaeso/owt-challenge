create table contact_skill_assoc (
    contact_id      int8 not null,
    skill_id        int8 not null,
    primary key (contact_id, skill_id)
);

create index idx_contact_skill_assoc_contact_id on contact_skill_assoc (contact_id);

create index idx_contact_skill_assoc_skill_id on contact_skill_assoc (skill_id);

alter table contact_skill_assoc
add constraint uq_contact_skill_assoc_contact_id foreign key (contact_id) references contact;

alter table contact_skill_assoc
add constraint uq_contact_skill_assoc_skill_id foreign key (skill_id) references skill;