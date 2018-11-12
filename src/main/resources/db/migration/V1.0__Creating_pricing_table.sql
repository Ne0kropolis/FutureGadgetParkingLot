CREATE TABLE PRICING (
  Pricing_ID INT,
  Pricing_Scheme_Number INT NOT NULL,
  Duration INT NOT NULL,
  Granularity VARCHAR(1) NOT NULL,
  Price DOUBLE,
  CONSTRAINT PK_PRICING PRIMARY KEY (Pricing_ID)
);