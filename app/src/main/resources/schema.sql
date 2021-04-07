create table logs (
    taskId serial primary key,
    taskDate date not null,
    task text not null,
    taskType text
);