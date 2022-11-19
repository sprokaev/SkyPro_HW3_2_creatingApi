ALTER TABLE student
    ADD CHECK ( age >= 16 ),
    ALTER COLUMN name SET NOT NULL,
    ADD CONSTRAINT unique_name UNIQUE (name),
    ALTER COLUMN age SET DEFAULT 20;
