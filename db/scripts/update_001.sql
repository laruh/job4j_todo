CREATE TABLE if not exists item (
	id SERIAL PRIMARY KEY,
	description TEXT,
	created Timestamp,
	done boolean
);