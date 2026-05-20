DROP TABLE IF EXISTS task_app.tasks;
CREATE TABLE task_app.tasks (
    id UUID PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT,
    status VARCHAR(255) NOT NULL,
    priority VARCHAR(255) NOT NULL,
    due_date DATE,
    created TIMESTAMP,
    updated TIMESTAMP
);
-- select * from  task_app.tasks

-- drop schema task_app.tasks 
-- select * from task_app.tasks 