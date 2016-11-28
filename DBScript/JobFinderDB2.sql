CREATE TABLE Company (
  idCompany serial NOT NULL,
  name varchar(60),
  address varchar(60),
  description varchar(60),
  CONSTRAINT Company_Pk PRIMARY KEY(idCompany)
);

CREATE TABLE Job (
  idJob serial NOT NULL ,
  Company_idCompany NOT NULL,
  title varchar(60),
  role varchar(60),
  requirements varchar(60),
  postDate varchar(60),
  rem varchar(60),
  CONSTRAINT Job_Pk PRIMARY KEY(idJob)
);

-- Triggers

CREATE FUNCTION Post_job() RETURNS trigger AS $Post_trigger$
BEGIN
	-- Verifica se o vendedor está cadastrado no banco
	IF NOT EXISTS(SELECT idCompany FROM Company WHERE NEW.company_idCompany = idCompany) THEN
	RAISE EXCEPTION 'Empresa/Pessoa não cadastrado!';
    END IF;
    RETURN NEW;
END;
$Post_trigger$ LANGUAGE plpgsql;

CREATE TRIGGER Post_job BEFORE INSERT OR UPDATE
ON Product
FOR EACH ROW EXECUTE
PROCEDURE Post_job();