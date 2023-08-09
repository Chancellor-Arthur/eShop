import { useMantineTheme } from "@mantine/core";
import { FC } from "react";
import NavLink, { NavLinkProps } from "./NavLink";

interface SubNavLinkProps extends NavLinkProps {}

const SubNavLink: FC<SubNavLinkProps> = (props) => {
  const theme = useMantineTheme();
  return <NavLink {...props} color={theme.colors.gray[6]} />;
};

export default SubNavLink;
