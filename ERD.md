# City Bike – ERD

Renders on GitHub (Mermaid). Column names are the real DB columns.

```mermaid
erDiagram
    STATION ||--o{ DOCK : "has (station_id)"
    DOCK |o--o{ BIKE : "currently holds (current_dock_id)"
    BIKE ||--o{ RENTAL : "rented in (bike_id)"
    RIDER ||--o{ RENTAL : "makes (rider_id)"
    DOCK ||--o{ RENTAL : "start (start_dock_id)"
    DOCK |o--o{ RENTAL : "end (end_dock_id)"

    STATION {
        int station_id PK "identity"
        varchar_100 name "NOT NULL"
        varchar_200 address
        decimal_9_6 latitude
        decimal_9_6 longitude
        boolean is_active "NOT NULL"
    }

    DOCK {
        int dock_id PK "identity"
        int dock_number "NOT NULL"
        varchar_20 status "NOT NULL, enum DockStatus"
        int station_id FK "NOT NULL"
    }

    BIKE {
        int bike_id PK "identity"
        varchar_50 serial_number UK "NOT NULL"
        varchar_20 bike_type "NOT NULL, enum BikeType"
        varchar_20 status_character "NOT NULL, enum BikeStatus"
        int current_dock_id FK "NULL while rented"
    }

    RIDER {
        int rider_id PK "identity"
        varchar_50 first_name "NOT NULL"
        varchar_50 last_name "NOT NULL"
        varchar_120 email UK "NOT NULL"
        varchar_255 password_hash "NOT NULL"
        varchar_30 phone UK
        varchar_20 membership_type "NOT NULL, enum MembershipType"
        timestamp registered_at "NOT NULL, set on insert"
    }

    RENTAL {
        int rental_id PK "identity"
        timestamp start_time "NOT NULL, set on insert"
        timestamp end_time "NULL until returned"
        decimal_10_2 cost
        int bike_id FK "NOT NULL"
        int rider_id FK "NOT NULL"
        int start_dock_id FK "NOT NULL"
        int end_dock_id FK "NULL until returned"
    }
```

## Relationships

| From | To | Cardinality | Notes |
|------|----|-------------|-------|
| Station | Dock | 1 – 0..N | Dock owns the FK; deleting a station cascades to its docks |
| Dock | Bike | 0..1 – 0..N | `Bike.currentDock`; null while the bike is out on a rental |
| Bike | Rental | 1 – 0..N | `Rental.bike`, required |
| Rider | Rental | 1 – 0..N | `Rental.rider`, required |
| Dock | Rental (start) | 1 – 0..N | `Rental.startDock`, required |
| Dock | Rental (end) | 0..1 – 0..N | `Rental.endDock`, null until the bike is returned |

## Table constraints

- `dock`: unique (`station_id`, `dock_number`)
- `rental`: check `end_time is null or end_time > start_time`
- Enums are stored as strings (`EnumType.STRING`):
  - `DockStatus`: AVAILABLE, OCCUPIED, OUT_OF_SERVICE
  - `BikeType`: STANDARD, ELECTRIC
  - `BikeStatus`: AVAILABLE, RENTED, MAINTENANCE
  - `MembershipType`: PAY_AS_YOU_GO, MONTHLY, ANNUAL
