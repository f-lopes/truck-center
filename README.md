# Truck-Center POC

Proof Of Concept
SOA Project : 

System to resolve trucks incidents :

1. Android app is used to submit trucks positions to a REST api (Mule ESB : /positionService)
2. Mule ESB endpoint (/positionService) adds positions received to an ActiveMQ queue (DRIVERS.POSITIONS)
2. Esper consume ActiveMQ queue and processes events. If truck is stuck on the road (incident), Esper detects it and submits a new incident alert to a REST api (webapp endpoint /rests/alerts)
4. A dashboard (/dashboard) displays active (or inactive) incidents and provides a workflow to resolve them using Activiti (incident.bpmn)

Technologies : 
- Mule ESB
    * ActiveMQ
    * Esper
- Spring Boot based webapp
    * Spring Security
    * Spring Data JPA
    * Activiti (embedded)
- Android application

---
Further improvements for V2 : 
    - Design
    - Push notifications
