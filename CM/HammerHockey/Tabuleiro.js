function Tabuleiro(contextoGrafico) {
	this.contexto = contextoGrafico;

	this.turnoDisponivel = true;

	this.jogadorAtual = 0;

	this.matriz = [
		[1, 0, 0, 0, 0],
		[2, 0, 8, 9, 10],
		[3, 0, 7, 0, 11],
		[4, 5, 6, 0, 12],
		[0, 0, 0, 0, 0],
	];

	this.jogadores = [];

	this.atualizar = function () {
		this.contexto.clearRect(0, 0, 1920, 1080);
		for (var i = 0; i < this.matriz.length; i++) {
			for (var j = 0; j < this.matriz[i].length; j++) {
				if (this.matriz[i][j]) {
					this.matriz[i][j].desenhar(contextoGrafico);
				}
			}
		}
	}

	this.adicionarJogador = function(posicao, cor) {
		var jogador = new Jogador(this.celulaInicial, posicao, cor);
		this.celulaInicial.atribuirJogador(jogador);
		return jogador;
	}

	this.inicializarTabuleiro = function() {
		var anterior = null;
		var posicaoFinal = this.determinarPosicaoFinal();
		for (var i = 1; i <= posicaoFinal; i++) {
			for (var j = 0; j < this.matriz.length; j++) {
				for (var k = 0; k < this.matriz[j].length; k++) {
					if (this.matriz[j][k] == i) {
						this.matriz[j][k] = new Celula(k, j, i);
						if (anterior != null) {
							anterior.sucessor = this.matriz[j][k];
						} else {
							this.celulaInicial = this.matriz[j][k];
						}
						anterior = this.matriz[j][k];
					}
				}
			}
		}
	}
	
	this.determinarPosicaoFinal = function() {
		var maior = 0;
		for (var i = 0; i < this.matriz.length; i++) {
			for (var j = 0; j < this.matriz[i].length; j++) {
				if (this.matriz[i][j] > maior) {
					maior = this.matriz[i][j];
				}
			}
		}
		return maior;
	}
	
 	this.inicializarTabuleiro();
}