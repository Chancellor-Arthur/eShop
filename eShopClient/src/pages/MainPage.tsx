import { FC } from 'react';
import { useQuery } from 'react-query';
import { getAll } from '@/helpers/fetchers/devices';
import { Container, SimpleGrid } from '@mantine/core';
import DeviceCard from '@/components/devices/DeviceCard';

const MainPage: FC = () => {
  const { data: devices } = useQuery('devices', () => getAll());

  return (
    <Container my="md">
      <SimpleGrid cols={4} breakpoints={[{ maxWidth: 'xs', cols: 1 }]}>
        {devices?.content?.map((device) => (
          <DeviceCard device={device} />
        ))}
      </SimpleGrid>
    </Container>
  );
};

export default MainPage;
