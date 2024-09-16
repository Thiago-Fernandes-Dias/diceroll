package com.mycompany.app;

public class GuessingManager
{
    private final int defaultAttempts = 3;

    private final DiceRoller _diceRoller;
    private final IUserInterface _userInterface;
    private int _attempts;

    public GuessingManager(int attempts, DiceRoller diceRoller, IUserInterface userInterface)
    {
        this._diceRoller = diceRoller;
        this._userInterface = userInterface;
        if (attempts <= 0)
        {
            _userInterface.showMessage(String.format(AppMessages.InvalidQtyOfAttemps, _defaultAttempts));
            _attempts = defaultAttempts;
        }
        else
        {
            _attempts = attempts;
        }
    }

    public async Task Run()
    {
        _diceRoller.RollDice();
        _userInterface.ShowMessage(string.Format(AppMessages.DiceRolled, _attempts));
        for (int i = 0; i < _attempts; i++)
        {
            var userChoice = _userInterface.ReadInteger(AppMessages.GuessTheNumber);
            if (userChoice == _diceRoller.RollResult)
            {
                _userInterface.ShowMessage(AppMessages.Win);
                return;
            }
            _userInterface.ShowMessage(AppMessages.WrongChoice);
        }
        _userInterface.ShowMessage(AppMessages.Lose);
    }
}