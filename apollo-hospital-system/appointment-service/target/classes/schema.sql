CREATE TABLE IF NOT EXISTS appointments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    patient_name VARCHAR(255) NOT NULL,
    doctor_id BIGINT NOT NULL,
    department VARCHAR(255) NOT NULL,
    appointment_time DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_on DATETIME NOT NULL,
    updated_on DATETIME NOT NULL
);
