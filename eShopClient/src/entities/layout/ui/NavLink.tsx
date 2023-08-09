import { FC, ReactNode } from 'react';
import { NavLink as OriginNavLink } from '@mantine/core';
import { Link, useLocation } from 'react-router-dom';

export interface NavLinkProps {
  to: string;
  label: string;
  icon?: ReactNode;
  color?: string;
}

const NavLink: FC<NavLinkProps> = ({ to, icon, label, color }) => {
  const location = useLocation();

  return (
    <OriginNavLink
      component={Link}
      styles={{ root: { borderRadius: 12, fontWeight: 600, color } }}
      to={to}
      active={location.pathname === to}
      label={label}
      icon={icon}
    />
  );
};

export default NavLink;
