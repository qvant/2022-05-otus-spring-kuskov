insert into schedules(id, name, seconds) values (-1, 'every minute', 60);
insert into schedules(id, name, seconds) values (-2, 'every hour', 3600);
insert into task_types(id, name) values (1, 'Oracle SQL');
insert into task_types(id, name) values (2, 'Oracle stored procedure');
insert into task_types(id, name) values (3, 'Postgresql');
insert into dependency_types(id, name) values (1, 'At least one success');
insert into dependency_types(id, name) values (2, 'At least one failure');
insert into dependency_types(id, name) values (3, 'All success');
insert into tasks (id, name, code, next_run, schedule_id, task_type_id) values (-1, 'Test', 'select null from dual', '2022-10-01',  -1, 1 );
insert into tasks (id, name, code, next_run, schedule_id, task_type_id) values (-2, 'Future task', 'select nul from dual', '2500-01-01',  -1, 1);
insert into tasks (id, name, code, next_run, schedule_id, task_type_id) values (-3, 'Start EOD task', 'pck_switch_eod.switch_eod', '2022-01-01',  -1, 2);
insert into tasks (id, name, code, next_run, schedule_id, task_type_id) values (-4, 'Oracle SQL always fall', 'select 1/0 from dual', '2022-10-01',  -2, 1 );
insert into tasks (id, name, code, next_run, schedule_id, task_type_id) values (-5, 'Oracle stored procedure always fall', 'select 1/0 from dual', '2022-10-01',  -2, 2 );
insert into tasks (id, name, code, next_run, schedule_id, task_type_id) values (-6, 'Test task, running on success', 'select null from dual', null,  null, 1);
insert into dependencies(id , dependency_type_id, task_parent_id , task_id) values (-1, 1, -1, -6);
insert into tasks (id, name, code, next_run, schedule_id, task_type_id) values (-7, 'Test task, running on failure', 'select null from dual', null,  null, 1);
insert into dependencies(id , dependency_type_id, task_parent_id , task_id) values (-2, 2, -4, -7);

insert into tasks (id, name, code, next_run, schedule_id, task_type_id) values (-8, 'Test Postgres task ', 'update process_test.eods set dt_eod = dt_eod + 1', '2022-10-01',  -1, 3);

insert into tasks (id, name, code, next_run, schedule_id, task_type_id) values (-9, 'Continue EOD task thread 1', 'pck_switch_eod.calc_some_data', null,  -1, 2);
insert into tasks (id, name, code, next_run, schedule_id, task_type_id) values (-10, 'Continue EOD task thread 2', 'pck_switch_eod.calc_other_data', null,  -1, 2);
insert into dependencies(id , dependency_type_id, task_parent_id , task_id) values (-3, 1, -3, -9);
insert into dependencies(id , dependency_type_id, task_parent_id , task_id) values (-4, 1, -3, -10);
insert into tasks (id, name, code, next_run, schedule_id, task_type_id) values (-11, 'Finish EOD task ', 'pck_switch_eod.close_eod', null,  -1, 2);
insert into dependencies(id , dependency_type_id, task_parent_id , task_id) values (-5, 3, -9, -11);
insert into dependencies(id , dependency_type_id, task_parent_id , task_id) values (-6, 3, -10, -11);
commit;