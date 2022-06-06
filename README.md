# BlueButton

## Table of Contents

- [Overview](#overview)
- [Main Window](#main-window)
- [Login Window](#login-window)
- [Selection Windows](#selection-windows)
  - [Room Selection Window](#room-selection-window)
  - [Time Selection Window](#time-selection-window)
  - [Game Selection Window](#game-selection-window)
  - [Snack Selection Window](#snack-selection-window)
- [Summary Window](#summary-window)

## Overview

BlueButton is a project that was conducted as part of an object-oriented programming class.
We wanted to make a kiosk that can be used in a board game cafe, and the purpose was to completely implement the reservation function. It has been worked with IntelliJ IDEA and is recommended to run it using JAVA 18.

## Main Window

![Main Window 01](sample_imgs/01_main.PNG)

The window that appears when you run the program, and you can check the remaining rooms. If a reserved room exists, it will be displayed in red as shown below.

![Main Window 02](sample_imgs/09_end.PNG)

## Login Window

![Login Window](sample_imgs/02_login.PNG)

Login is divided into member login and non-member login. Membership information is stored in 'member.txt' in the top-level directory.

## Selection Windows

![Selection Windows](sample_imgs/03_option.PNG)

This is the driveway for selecting rooms, time, games, and snacks after logging in. Once all four options have been selected, the payment button will be activated.

### Room Selection Window

![Room Selection Window](sample_imgs/04_room.PNG)

The window allows you to select the game room you want. The selectable room appears in green, and the non-selectable room appears in red.

### Time Selection Window

![Time Selection Window](sample_imgs/05_time.PNG)

The window allows you to select the desired time.

### Game Selection Window

![Game Selection Window](sample_imgs/06_game.PNG)

The window allows you to select the game what you want. Selecting a game in the table displays information about the game and recommended games.

### Snack Selection Window

![Snack Selection Window](sample_imgs/07_snack.PNG)

This window allows you to order snacks. When you select the product you want to order, an additional menu appears where you can select detailed options.

## Summary Window

![Summary View](sample_imgs/08_myPage.PNG)

This window shows a summary of previously selected orders.
