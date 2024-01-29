CREATE TABLE Facility (
    id INT PRIMARY KEY,
    name VARCHAR2(255),
    address_streetname VARCHAR2(255),
    address_postalcode VARCHAR2(255),
    address_cityname VARCHAR2(255),
    phone VARCHAR2(255),
    email VARCHAR2(255),
    fax VARCHAR2(255),
    website VARCHAR2(255),
    maxvaccinesperhour NUMBER
);

CREATE TABLE Employee (
    id INT,
    email VARCHAR(255) PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    position VARCHAR(255),
    phone VARCHAR(255)
);


DROP TABLE Facility;
DROP TABLE Employee;

SELECT * FROM Facility;
SELECT * FROM Employee;