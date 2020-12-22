import { Http, Headers, URLSearchParams } from '@angular/http';
import { Injectable } from '@angular/core';

import 'rxjs/add/operator/toPromise';
import { environment } from 'environments/environment';

export class UserFilter {
  username: string;
  name: string;
  email: string;
  page = 0;
  size = 10;
}

@Injectable()
export class UserService {

  usersUrl: string;

  constructor(private http: Http) {
    this.usersUrl = environment.apiUrl + '/users'
  }

  search(filter: UserFilter): Promise<any> {
    const params = new URLSearchParams();

    params.set('page', filter.page.toString());

    params.set('size', filter.size.toString());

    if (filter.username) {
      params.set('username', filter.username);
    }

    if (filter.name) {
      params.set('name', filter.name);
    }

    if (filter.email) {
      params.set('email', filter.email);
    }

    return this.http.get(this.usersUrl, { search: params })
      .toPromise()
      .then(response => {
        const responseJson = response.json();
        const users = response.json().content;

        const result = {
          users,
          totalElements: responseJson.totalElements
        }

        return result;
      })
  }

}
