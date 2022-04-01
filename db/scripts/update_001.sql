CREATE TABLE if not exists j_item (
	id SERIAL PRIMARY KEY,
	description TEXT,
	created Timestamp,
	done boolean
);