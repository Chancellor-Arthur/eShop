import { withMantine } from "./withMantine";
import compose from "compose-function";
import { withRouter } from "./withRouter";

export const withProviders = compose(withMantine, withRouter);
