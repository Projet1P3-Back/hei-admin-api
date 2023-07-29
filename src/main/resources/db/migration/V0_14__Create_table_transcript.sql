
do
$$
    begin
        if not exists(select from pg_type where typname = 'semester') then
            create type semester as enum ('s1', 's2', 's3', 's4');
        end if;
    end
$$;
create table if not exists "transcript"
(
    id              varchar
        constraint transcript_pk        primary key default  uuid_generate_v4(),
    student_id      varchar
        constraint student_id_fk             references "user" (id),
    semester        semester,
    academicYear    varchar,
    isDefinitive    varchar,
    creationDatetime timestamp with time zone not null default now()

);
