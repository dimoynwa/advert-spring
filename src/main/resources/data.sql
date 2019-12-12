INSERT INTO public.ss_user(
	id, created_at, updated_at, created_by, updated_by, active, email, first_name, last_name, password, username)
	VALUES (1, now(), now(), null, null, true, 'admin@gmail.com', 'Dimo', 'Drangov', '$2y$13$ulXalfmGqBsSxsJrTtGrLeJE4aB5ivVL19a3Y0mXkOaDbkISu7FRW', 'admin') ON CONFLICT DO NOTHING;