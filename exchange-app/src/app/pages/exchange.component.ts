import {Component, OnInit} from '@angular/core';
import {ExchangeService} from '../services/exchange.service';

@Component({
  selector: 'app-exchange-page',
  templateUrl: './exchange.component.html',
  styleUrls: ['./exchange.component.css']
})
export class ExchangeComponent implements OnInit {

  errorLabel: string = "";
  amountLabel: string = "";
  amountWithExchangeLabel: string = "";
  currencyOriginLabel: string = "";
  currencyDestinyLabel: string = "";
  amountExchangeLabel: string = "";

  constructor(public exchangeService: ExchangeService) {
  }

  ngOnInit(): void {
  }

  ngGetExchange(amount: number, currencyOrigin: string, currencyDestiny: string) {
    this.exchangeService.exchange(amount, currencyOrigin, currencyDestiny).subscribe(
      (data: any) => {
        this.errorLabel = "";
        this.amountLabel = data.data.amount;
        this.amountWithExchangeLabel = data.data.amountWithExchange;
        this.currencyOriginLabel = data.data.currencyOrigin;
        this.currencyDestinyLabel = data.data.currencyDestiny;
        this.amountExchangeLabel = data.data.amountExchange;
      }, (error: any) => {
        this.errorLabel = "Por favor revise que los datos ingresados sean correctos. En caso el error persista, vuelva intentarlo luego.";
        this.ngCleanForm();
      }
    );
  }

  ngCleanForm() {
    this.amountLabel = "";
    this.amountWithExchangeLabel = "";
    this.currencyOriginLabel = "";
    this.currencyDestinyLabel = "";
    this.amountExchangeLabel = "";
  }

}
