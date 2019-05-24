-- Visitors Schema
-- !Ups
CREATE TABLE visitor (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    date_hour varchar(255) NOT NULL,
    timestamp bigint(20) NOT NULL,
    user bigint(20) NOT NULL,
    event varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE INDEX data_hour_index ON visitor(date_hour);

-- !Downs
DROP TABLE visitor;
