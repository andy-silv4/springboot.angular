import { UsersPage } from './app.po';

describe('users App', () => {
  let page: UsersPage;

  beforeEach(() => {
    page = new UsersPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
