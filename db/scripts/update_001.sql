CREATE TABLE if not exists j_item (
	id SERIAL PRIMARY KEY,
	name TEXT,
	description TEXT,
	created Timestamp,
	done boolean
);