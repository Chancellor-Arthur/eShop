import { withMantine } from './withMantine';
import compose from 'compose-function';
import { withRouter } from './withRouter';
import { withAuth } from '@/app/providers/withAuth';
import { withQueryClient } from '@/app/providers/withQueryClient';

export const withProviders = compose(withQueryClient, withMantine, withRouter, withAuth);
