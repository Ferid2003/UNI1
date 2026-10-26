# City Bike – UML Class Diagram

Renders on GitHub (Mermaid). Layers: controller → service → repository → entity (controllers depend only on `AbstractCrudService`, services only on `JpaRepository`); DTOs cross the controller/service boundary.

```mermaid
classDiagram
    direction LR

    %% ---------- Entities ----------
    class Station {
        Integer id
        String name
        String address
        BigDecimal latitude
        BigDecimal longitude
        Boolean isActive
        addDock(Dock)
        removeDock(Dock)
    }
    class Dock {
        Integer id
        Integer dockNumber
        DockStatus status
    }
    class Bike {
        Integer id
        String serialNumber
        BikeType bikeType
        BikeStatus statusCharacter
    }
    class Rider {
        Integer id
        String firstName
        String lastName
        String email
        String passwordHash
        String phone
        MembershipType membershipType
        Instant registeredAt
    }
    class Rental {
        Integer id
        Instant startTime
        Instant endTime
        BigDecimal cost
    }

    class BikeType { <<enumeration>> STANDARD ELECTRIC }
    class BikeStatus { <<enumeration>> AVAILABLE RENTED MAINTENANCE }
    class DockStatus { <<enumeration>> AVAILABLE OCCUPIED OUT_OF_SERVICE }
    class MembershipType { <<enumeration>> PAY_AS_YOU_GO MONTHLY ANNUAL }

    Station "1" *-- "0..*" Dock : docks
    Dock "0..1" <-- "0..*" Bike : currentDock
    Rider "1" <-- "0..*" Rental : rider
    Bike "1" <-- "0..*" Rental : bike
    Dock "1" <-- "0..*" Rental : startDock
    Dock "0..1" <-- "0..*" Rental : endDock

    Dock ..> DockStatus
    Bike ..> BikeType
    Bike ..> BikeStatus
    Rider ..> MembershipType

    %% ---------- Repositories ----------
    class JpaRepository~E, Integer~ { <<interface>> }
    class StationRepository { <<interface>> }
    class DockRepository { <<interface>> }
    class BikeRepository { <<interface>> }
    class RiderRepository { <<interface>> }
    class RentalRepository { <<interface>> }

    JpaRepository <|-- StationRepository
    JpaRepository <|-- DockRepository
    JpaRepository <|-- BikeRepository
    JpaRepository <|-- RiderRepository
    JpaRepository <|-- RentalRepository

    StationRepository ..> Station
    DockRepository ..> Dock
    BikeRepository ..> Bike
    RiderRepository ..> Rider
    RentalRepository ..> Rental

    %% ---------- Services ----------
    class AbstractCrudService~E, REQ, RES~ {
        <<abstract>>
        #JpaRepository~E, Integer~ repo
        +findAll() List~RES~
        +findById(id) RES
        +create(req) RES
        +update(id, req) RES
        +delete(id)
        #get(id) E
        #apply(entity, req)* E
        #toResponse(entity)* RES
    }
    class StationService
    class DockService
    class BikeService
    class RiderService
    class RentalService

    AbstractCrudService <|-- StationService : E=Station
    AbstractCrudService <|-- DockService : E=Dock
    AbstractCrudService <|-- BikeService : E=Bike
    AbstractCrudService <|-- RiderService : E=Rider
    AbstractCrudService <|-- RentalService : E=Rental

    AbstractCrudService --> JpaRepository : repo
    DockService --> JpaRepository : stations
    BikeService --> JpaRepository : docks
    RentalService --> JpaRepository : bikes, riders, docks

    %% ---------- Controllers ----------
    class AbstractCrudController~REQ, RES~ {
        <<abstract>>
        +findAll() List~RES~
        +findById(id) RES
        +create(req) RES
        +update(id, req) RES
        +delete(id)
    }
    class StationController { <<RestController>> }
    class DockController { <<RestController>> }
    class BikeController { <<RestController>> }
    class RiderController { <<RestController>> }
    class RentalController { <<RestController>> }

    AbstractCrudController --> AbstractCrudService
    AbstractCrudController <|-- StationController
    AbstractCrudController <|-- DockController
    AbstractCrudController <|-- BikeController
    AbstractCrudController <|-- RiderController
    AbstractCrudController <|-- RentalController

    %% ---------- DTOs (one Request + Response per entity) ----------
    class StationDTOs { StationRequest StationResponse }
    class DockDTOs { DockRequest DockResponse }
    class BikeDTOs { BikeRequest BikeResponse }
    class RiderDTOs { RiderRequest RiderResponse }
    class RentalDTOs { RentalRequest RentalResponse }

    StationController ..> StationDTOs
    DockController ..> DockDTOs
    BikeController ..> BikeDTOs
    RiderController ..> RiderDTOs
    RentalController ..> RentalDTOs
```

`Found` (package-private helper in `service`) and `CityBikeApplication` (Spring Boot entry point) are omitted for clarity.
