insert into schedules(id, name, seconds) values (-1, 'every minute', 60);
insert into tasks (id, name, code, next_run, schedule_id) values (-1, 'Test', 'select nul from dual', '2022-10-01',  -1);
insert into tasks (id, name, code, next_run, schedule_id) values (-2, 'Future task', 'select nul from dual', '2500-01-01',  -1);
commit;