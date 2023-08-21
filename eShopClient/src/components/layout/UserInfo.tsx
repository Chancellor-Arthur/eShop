import { MoreVert20Icon } from "@/shared/icons";
import { ActionIcon, Avatar, Group, List, Text } from "@mantine/core";
import { FC } from "react";

const UserInfo: FC = () => {
  return (
    <Group position="apart">
      <Group spacing="xs">
        <Avatar color="cyan" radius="xl">
          СД
        </Avatar>
        <List spacing="xs">
          <Text weight={600}>Смирнов Даниил</Text>{" "}
          <Text size="xs" color="gray.5">
            stilwuc@mail.ru
          </Text>
        </List>
      </Group>
      <ActionIcon>
        <MoreVert20Icon />
      </ActionIcon>
    </Group>
  );
};

export default UserInfo;
