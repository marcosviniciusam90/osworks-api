import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Cliente } from 'src/app/model/cliente.model';
import { ClienteService } from 'src/app/service/cliente.service';

@Component({
  selector: 'app-cadastrar-cliente',
  templateUrl: './cadastrar-cliente.component.html',
  styleUrls: ['./cadastrar-cliente.component.css']
})
export class CadastrarClienteComponent implements OnInit {

  displayedColumns: string[] = ['id', 'nome', 'email', 'telefone'];
  dataSource = new MatTableDataSource<Cliente>();

  constructor(
    private clienteService:ClienteService
  ) { }

  ngOnInit() {
    this.getClientes();   
  }

  getClientes() {
    let resp = this.clienteService.getClientes();
    resp.subscribe(data => {
      console.log(data);
    })
  }
  

}
