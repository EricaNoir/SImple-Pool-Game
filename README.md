1. How to run the code
    gradle run: Load default config from resources folder.
    gradle run --args="'insert_config_file_path'": load custom config.

2. Features
    Pockets and More Coloured Balls,
    Difficulty Level,
    Time and Score,
    Undo and Cheat

3. Design patterns
    - Singleton for Time and Score
        PoolGame.Singleton.GameTimer
        PoolGame.Singleton.ScoreBoard
        PoolGame.Singleton.Task

    - Memento for Undo
        PoolGame.Memento.Caretaker
        PoolGame.Memento.Memento
        PoolGame.Memento.Originator

    - Observer for Cheat
        PoolGame.Observer.Subject (Interface)
        PoolGame.Observer.CheatSubject
        PoolGame.Observer.CheatObserver (Interface)
        PoolGame.Observer.BallObserver
        PoolGame.Observer.ScoreObserver
        PoolGame.Observer.StateKeeperObserver

4. Instruction
    - Select difficulty level
        The game is in easy mode in default.
        Press different number keys on you keyboard (either numpad or not) to change difficulty level.
        Press '1' for EASY mode, '2' for NORMAL mode and '3' for HARD mode.

    - undo
        Press 'Z' to undo your last operation.
        *operation includes hit cue ball, change mode and cheat.

    - Cheat
        Press different keys to make one colour of balls disappear and gain corresponding score.
        * Red: 'R', Yellow: 'Y', Green: 'G', Brown: 'W'
          Blue: 'B', Purple: 'P', Black: 'K', Orange: 'O'

5. Others
    The sample json configuration files are in src/main/resources, please put new config files into this directory and keep the file name the same.