# System overview

This repository contains an enterprise-style modular monolith for a staffing/agency CRM.

## Branching & Quality Gates
- main: production
- develop: integration
- feature/*: changes via PR
- CI must be green before merge

## High-level modules (initial)
- identity (users, roles, auth)
- crm (partners/families, caregivers, assignments, applications)
- activity (calls, notes, follow-ups)
- reporting (later)
- ai (later, async jobs only)
