package Ex3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface IMediator {
    void sendMessage(String message, User sender, String channel);
    void addUser(User user, String channel);
    void removeUser(User user, String channel);
}

class ChatMediator implements IMediator {
    private final Map<String, List<User>> channels = new HashMap<>();

    @Override
    public void sendMessage(String message, User sender, String channel) {
        if (!channels.containsKey(channel)) {
            System.out.println("Канал " + channel + " не существует.");
            return;
        }
        List<User> users = channels.get(channel);
        for (User user : users) {
            if (user != sender) {
                user.receive(message, channel);
            }
        }
    }

    @Override
    public void addUser(User user, String channel) {
        channels.computeIfAbsent(channel, k -> new ArrayList<>()).add(user);
        user.setMediator(this);
        notifyUsers(user, channel, true);
    }

    @Override
    public void removeUser(User user, String channel) {
        if (channels.containsKey(channel)) {
            channels.get(channel).remove(user);
            notifyUsers(user, channel, false);
        }
    }

    private void notifyUsers(User user, String channel, boolean isJoin) {
        String action = isJoin ? "вошел в чат" : "вышел из чата";
        sendMessage(user.getName() + " " + action + " на канале " + channel, user, channel);
    }
}

interface IUser {
    void send(String message, String channel);
    void receive(String message, String channel);
    String getName();
    void setMediator(IMediator mediator);
}

class User implements IUser {
    private final String name;
    private IMediator mediator;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void send(String message, String channel) {
        System.out.println(name + " отправил сообщение: " + message + " на канале " + channel);
        mediator.sendMessage(message, this, channel);
    }

    @Override
    public void receive(String message, String channel) {
        System.out.println(name + " получил сообщение на канале " + channel + ": " + message);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setMediator(IMediator mediator) {
        this.mediator = mediator;
    }
}

public class Main3 {
    public static void main(String[] args) {
        ChatMediator chatMediator = new ChatMediator();

        User user1 = new User("Аружан");
        User user2 = new User("Бекжан");
        User user3 = new User("Саян");

        chatMediator.addUser(user1, "Общий");
        chatMediator.addUser(user2, "Общий");
        chatMediator.addUser(user3, "Общий");

        user1.send("Сәлем всем!", "Общий");
        user2.send("Сәлем, Аружан!", "Общий");

        chatMediator.removeUser(user2, "Общий");

        user3.send("Где Бекжан?", "Общий");
        user1.send("Бекжан вышел.", "Общий");

        chatMediator.addUser(user2, "Общий");
        user2.send("Я вернулся!", "Общий");
    }
}
