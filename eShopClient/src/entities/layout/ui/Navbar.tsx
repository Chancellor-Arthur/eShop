import { FC } from "react";
import { Navbar as OriginNavbar, Group, ActionIcon, Divider, List } from "@mantine/core";
import { MenuHide24Icon } from "@/shared/icons";
import { Link } from "react-router-dom";
import { navBarLinks, navBarSubLinks } from "@/entities/layout/model/navBar";
import NavLink from "./NavLink";
import UserInfo from "./UserInfo";
import SubNavLink from "./SubNavLink";

const Navbar: FC = () => {
  return (
    <OriginNavbar
      width={{ base: 240 }}
      styles={(theme) => ({
        main: { backgroundColor: theme.colors.gray[0] },
      })}
    >
      <OriginNavbar.Section p="xs">
        <Group position="apart">
          <Link to="/">
            <Group spacing="xs">Logo</Group>
          </Link>
          <ActionIcon>
            <MenuHide24Icon />
          </ActionIcon>
        </Group>
      </OriginNavbar.Section>
      <Divider />
      <OriginNavbar.Section grow>
        <List p="xs">
          {navBarLinks.map((link) => (
            <NavLink label={link.label} to={link.href} icon={link.icon} key={link.href} />
          ))}
        </List>
        <Divider my="xs" />
      </OriginNavbar.Section>
      <OriginNavbar.Section p="xs">
        <List p="xs">
          {navBarSubLinks.map((link) => (
            <SubNavLink label={link.label} to={link.href} icon={link.icon} key={link.href} />
          ))}
        </List>
      </OriginNavbar.Section>
      <Divider />
      <OriginNavbar.Section p="md">
        <UserInfo />
      </OriginNavbar.Section>
    </OriginNavbar>
  );
};

export default Navbar;
