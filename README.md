# GameNight Bot
***
*Notice: this bot is specially designed for a private discord. As time goes on it will be made more modular and robust, but this repo in its current state is effectively an internal tool, which is unpolished and rough due to its original purpose as a single-purpose internal-use program, only available for use by specific users who know the quirks.*
***
*Other Notice: The bot is undergoing a full re-write right now in order to facilitate the new modularity and robustness required by an open-sourced version, as well as new features. As a result, the version on github right now is incredibly feature-incomplete and lacking core functionality (as was the old one). Do not expect a complete and functional bot from using this.*
***
## Purpose
GameNight is a Discord bot intended to run a fixed scheduled game-night event. It alerts every Friday at 10:30 in a specialized channel (this will be adjusted using a GUI in a later version) and pings the "gamers" role, which is self-assignable with `?role give Gamers`. It can be used to self-assign roles for certain games, as flexible events set within the server for specialized games are planned.

It can also store an unlimited number of games, which contain game modes, which then contain the names of maps (this container can be used for anything). For example, in our server, it looks something like this:

```
	Xonotic
		TDM
			Vehicle
			Instagib
			Regular
		DM
			Vehicle
			Instagib
			Regular
		CTF
			Vehicle
			Instagib
			Regular
	Warframe
		Survival
			Void
			Corpus
			Kuva
			Infested
		Defense
			Void
			Corpus
			Kuva
			Infested
```
Games will have roles added to them, which will allow game nights to roll for specific games and then mention the associated roles.

## Usage
Please see the Wiki for documentation and user guides. Docs coming soon.
In order to start the bot, place a text file called "token" in the same folder as the bot, or simply directly place the token in your source code. The token was offloaded to a separate file here so that it does not need to be published in the repository.
***
### Libraries
The bot uses Javacord.
