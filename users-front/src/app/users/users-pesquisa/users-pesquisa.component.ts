import { UserFilter, UserService } from '../user.service';
import { Component, OnInit } from '@angular/core';
import { LazyLoadEvent } from 'primeng/components/common/lazyloadevent';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-users-pesquisa',
  templateUrl: './users-pesquisa.component.html',
  styleUrls: ['./users-pesquisa.component.css']
})
export class UsersPesquisaComponent implements OnInit {

  totalElements = 0;
  filter = new UserFilter();
  users = [];

  constructor(private userService: UserService) { }

  ngOnInit() {
    // this.search();
  }

  search(page = 0) {
    this.filter.page = page;

    this.userService.search(this.filter)
      .then(result => {
        this.users = result.users;
        this.totalElements = result.totalElements;
      });
  }

  test(event: LazyLoadEvent) {
    const page = event.first / event.rows;
    this.search(page);
  }

}
