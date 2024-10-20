package Ex1;

import java.util.ArrayList;
import java.util.List;
interface ICommand {
    void execute();
    void undo();
}
class Light {
    public void on() {
        System.out.println("Свет включен");
    }

    public void off() {
        System.out.println("Свет выключен");
    }
}
class TV {
    public void on() {
        System.out.println("Телевизор включен");
    }

    public void off() {
        System.out.println("Телевизор выключен");
    }
}
class AirConditioner {
    public void on() {
        System.out.println("Кондиционер включен");
    }

    public void off() {
        System.out.println("Кондиционер выключен");
    }
}
class LightOnCommand implements ICommand {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}

class LightOffCommand implements ICommand {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }

    public void undo() {
        light.on();
    }
}
class TVOnCommand implements ICommand {
    private TV tv;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    public void execute() {
        tv.on();
    }

    public void undo() {
        tv.off();
    }
}

class TVOffCommand implements ICommand {
    private TV tv;

    public TVOffCommand(TV tv) {
        this.tv = tv;
    }

    public void execute() {
        tv.off();
    }

    public void undo() {
        tv.on();
    }
}
class ACOnCommand implements ICommand {
    private AirConditioner ac;

    public ACOnCommand(AirConditioner ac) {
        this.ac = ac;
    }

    public void execute() {
        ac.on();
    }

    public void undo() {
        ac.off();
    }
}

class ACOffCommand implements ICommand {
    private AirConditioner ac;

    public ACOffCommand(AirConditioner ac) {
        this.ac = ac;
    }

    public void execute() {
        ac.off();
    }

    public void undo() {
        ac.on();
    }
}
class RemoteControl {
    private ICommand[] slots;
    private ICommand lastCommand;

    public RemoteControl(int numSlots) {
        slots = new ICommand[numSlots];
    }

    public void setCommand(int slot, ICommand command) {
        slots[slot] = command;
    }

    public void pressButton(int slot) {
        if (slots[slot] != null) {
            slots[slot].execute();
            lastCommand = slots[slot];
        } else {
            System.out.println("Команда не назначена для слота " + slot);
        }
    }

    public void pressUndo() {
        if (lastCommand != null) {
            lastCommand.undo();
        } else {
            System.out.println("Нет команды для отмены");
        }
    }
}
class MacroCommand implements ICommand {
    private List<ICommand> commands;

    public MacroCommand() {
        commands = new ArrayList<>();
    }

    public void addCommand(ICommand command) {
        commands.add(command);
    }

    public void execute() {
        for (ICommand command : commands) {
            command.execute();
        }
    }

    public void undo() {
        for (ICommand command : commands) {
            command.undo();
        }
    }
}
public class Main {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl(5);

        Light livingRoomLight = new Light();
        TV livingRoomTV = new TV();
        AirConditioner livingRoomAC = new AirConditioner();

        ICommand lightOn = new LightOnCommand(livingRoomLight);
        ICommand lightOff = new LightOffCommand(livingRoomLight);
        ICommand tvOn = new TVOnCommand(livingRoomTV);
        ICommand tvOff = new TVOffCommand(livingRoomTV);
        ICommand acOn = new ACOnCommand(livingRoomAC);
        ICommand acOff = new ACOffCommand(livingRoomAC);

        remote.setCommand(0, lightOn);
        remote.setCommand(1, lightOff);
        remote.setCommand(2, tvOn);
        remote.setCommand(3, tvOff);
        remote.setCommand(4, acOn);

        remote.pressButton(0);
        remote.pressButton(2);
        remote.pressUndo();
        remote.pressButton(4);
        remote.pressUndo();

        MacroCommand macro = new MacroCommand();
        macro.addCommand(lightOn);
        macro.addCommand(tvOn);
        remote.setCommand(3, macro);
        remote.pressButton(3);
    }
}
