CREATE TABLE Company (
  idCompany serial NOT NULL,
  companyName varchar(60),
  companyAddress varchar(60),
  companyDescription varchar(60),
  CONSTRAINT Company_Pk PRIMARY KEY(idCompany)
);

CREATE TABLE Job (
  idJob serial NOT NULL ,
  Company_idCompany serial NOT NULL,
  jobTitle varchar(60),
  jobRole varchar(60),
  jobRequirements varchar(60),
  jobPostDate varchar(60),
  jobRem varchar(60),
  CONSTRAINT Job_Pk PRIMARY KEY(idJob)
);

