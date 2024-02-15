CREATE TABLE credit (
  id VARCHAR(255) NOT NULL,
   credit_code BINARY(16) NOT NULL,
   credit_value DECIMAL NULL,
   day_first_installment date NULL,
   number_of_installments INT NOT NULL,
   status SMALLINT NULL,
   customer_id VARCHAR(255) NULL,
   CONSTRAINT pk_credit PRIMARY KEY (id)
);

ALTER TABLE credit ADD CONSTRAINT uc_credit_credit_code UNIQUE (credit_code);

ALTER TABLE credit ADD CONSTRAINT FK_CREDIT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES cliente (id);