# Functional Requirements – Text-Based Adventure Game

## System Overview

The system is a single-player, text-based adventure game where the player explores a fantasy world through written descriptions and choices.  
The player’s decisions, character attributes, and interactions influence story progression, available actions, and the final outcome of the game.

The system focuses on narrative choice, exploration, and consequence-based gameplay.

---

## Actors

### Player
The player controls one character and interacts with the game by selecting actions, making decisions, and managing their character.

### System
The system presents story text, validates player choices, updates the game state, and enforces game rules.

---

## Core Domain Entities

### Players Character
- Has a name, race, and character class
- Has stats such as health, strength, dexterity, and intelligence
- Can carry items, accept quests, and affect storyline aswell as ending

### Character Race
- Modifies the player’s base attributes
- May unlock or restrict certain interactions

### Character Class
- Defines the player’s role and strengths
- Influences combat effectiveness and dialogue options

### Location
- Represents a place in the game world
- Contains descriptions, creatures, items, and available choices
- Is connected to other locations via directions

### Creature / NPC
- Represents living entities in the world
- NPCs may interact, give quests, or become hostile
- Creatures can be defeated and removed from play

### Item
- Can be collected, used, equipped or deleted
- May be required to unlock choices or complete quests

### Choice
- Represents an action available to the player
- May include movement, interaction, combat, or item usage
- Can be restricted by requirements such as class, race, items, or story state

---

## Functional Requirements

### Gameplay Flow
- The system shall allow the player to start and progress through a game session
- The system shall present narrative text describing the current situation
- The system shall allow the player to select from available choices
- The system shall update the game state after each choice
- The system shall allow the player to save and load the game

### Navigation
- The player shall be able to move between connected locations
- Movement shall only be possible where a valid connection exists

### Choices and Conditions
- Only valid choices shall be presented to the player
- Choice availability may depend on player attributes, inventory, NPC state, or story progress
- Choices may become unavailable after being taken prior in the game

### Combat
- The system shall support combat between the player and hostile creatures
- Combat shall resolve based on character stats
- Defeated creatures shall no longer be avaliable or interact with the player

### Inventory and Quests
- The system shall allow the player to collect and use items
- The system shall support quests that affect story progression
- Completing quests may unlock new choices or locations

### Story Endings
- The system shall support multiple endings
- The ending shall be determined by the player’s actions and decisions

---

## Rules and Limitations

- The system is single-player only
- No graphical interface is required
- Player health cannot exceed maximum health or fall below zero (Game over)
- Dead creatures cannot act or be interacted with
- The game must remain consistent with the player’s choices and actions
