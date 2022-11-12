package view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import controller.UsuarioController;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class MenuUsuario {

	private static final int OPCAO_MENU_CADASTRAR_USUARIO = 1;
	private static final int OPCAO_MENU_CONSULTAR_USUARIO = 2;
	private static final int OPCAO_MENU_ATUALIZAR_USUARIO = 3;
	private static final int OPCAO_MENU_EXCLUIOR_USUARIO = 4;
	private static final int OPCAO_MENU_VOLTAR = 9;

	private static final int OPCAO_MENU_CONSULTAR_TODOS_USUARIOS = 1;
	private static final int OPCAO_MENU_CONSULTAR_UM_USUARIO = 2;
	private static final int OPCAO_MENU_CONSULTAR_USUARIO_VOLTAR = 9;

	Scanner teclado = new Scanner(System.in);

	public void apresentarMenuUsuario() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_CONSULTAR_USUARIO_VOLTAR) {
			switch (opcao) {
			case OPCAO_MENU_CADASTRAR_USUARIO: {
				UsuarioVO usuarioVO = new UsuarioVO();
				this.cadastrarUsuario(usuarioVO);
				break;
			}
			case OPCAO_MENU_CONSULTAR_USUARIO: {
				this.consultarUsuario();
				break;
			}
			case OPCAO_MENU_ATUALIZAR_USUARIO: {
				this.atualizarUsuario();
				break;
			}
			case OPCAO_MENU_EXCLUIOR_USUARIO: {
				this.excluirUsuario();
				break;
			}
			default: {
				System.out.println("\nOpção inválida!");
			}
			}
			opcao = this.apresentarOpcoesMenu();
		}

	}

	private int apresentarOpcoesMenu() {
		System.out.println("\n--------Sistema FoodTruck-----------");
		System.out.println("---------Menu de Usuário---------");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_CADASTRAR_USUARIO + " - Cadastrar Usuário");
		System.out.println(OPCAO_MENU_CONSULTAR_USUARIO + " - Consultar Usuário");
		System.out.println(OPCAO_MENU_ATUALIZAR_USUARIO + " - Atualizar Usuário");
		System.out.println(OPCAO_MENU_EXCLUIOR_USUARIO + " - Excluir Usuário");
		System.out.println(OPCAO_MENU_VOLTAR + " - Voltar");
		System.out.println("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

	// MÉTODO QUE CADASTRA UM USUÁRIO EXTERNO
	public void cadastrarNovoUsuario(UsuarioVO usuarioVO) {
		this.cadastrarUsuario(usuarioVO);
	}

	private void cadastrarUsuario(UsuarioVO usuarioVO) {
		if (usuarioVO.getTipoUsuario() == null) {
			do {
				usuarioVO.setTipoUsuario(TipoUsuarioVO.getTipoUsuarioVOPorValor(this.apresentarOpcoesTipoUsuario()));
			} while (usuarioVO.getTipoUsuario() == null);
		}
		System.out.println("\nDigite o Nome: ");
		usuarioVO.setNome(teclado.nextLine());
		System.out.println("Digite o CPF: ");
		usuarioVO.setCpf(teclado.nextLine());
		System.out.println("Digite o e-mail: ");
		usuarioVO.setEmail(teclado.nextLine());
		System.out.println("Digite o Telefone: ");
		usuarioVO.setTelefone(teclado.nextLine());
		usuarioVO.setDataCadastro(LocalDateTime.now());
		System.out.println("Digite o Login: ");
		usuarioVO.setLogin(teclado.nextLine());
		System.out.println("Digite a Senha: ");
		usuarioVO.setSenha(teclado.nextLine());
		
		if(this.validarCamposCadastro(usuarioVO)) {
			UsuarioController usuarioController = new UsuarioController();
			usuarioVO = usuarioController.cadastrarUsuarioController(usuarioVO);
			if(usuarioVO.getIdUsuario() != 0) {
				System.out.println("Usuario Cadastrado com Sucesso!");
			} else {
				System.out.println("Não foi Possivel cadastrar o Usuário!");
			}
		}
	}

	

	private int apresentarOpcoesTipoUsuario() {
		UsuarioController usuarioController = new UsuarioController();
		ArrayList<TipoUsuarioVO> listaTipoUsuarioVO = usuarioController.consultarTipoUsuario();
		System.out.println("\n----- Tipo de Usuários -----");
		System.out.println("Opções: ");
		for (int i = 0; i < listaTipoUsuarioVO.size(); i++) {
			System.out.println(listaTipoUsuarioVO.get(i).getValor() + " - " + listaTipoUsuarioVO.get(i));
		}
		System.out.println("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

	private boolean validarCamposCadastro(UsuarioVO usuarioVO) {
		boolean resultado = true;
		System.out.println();
		if(usuarioVO.getNome() == null || usuarioVO.getNome().isEmpty()) {
			System.out.println("O campo nome é obrigatório");
			resultado = false;
		}
		if(usuarioVO.getCpf() == null || usuarioVO.getCpf().isEmpty()) {
			System.out.println("O campo CPF é obrigatório");
			resultado = false;
		}
		if(usuarioVO.getEmail() == null || usuarioVO.getEmail().isEmpty()) {
			System.out.println("O campo e-mail é obrigatório");
			resultado = false;
		}
		if(usuarioVO.getTelefone() == null || usuarioVO.getTelefone().isEmpty()) {
			System.out.println("O campo Telefone é obrigatório");
			resultado = false;
		}
		if(usuarioVO.getDataCadastro() == null) {
			resultado = false;
			System.out.println("O campo Data de Cadastro é obrigatório");
		}
		if(usuarioVO.getLogin() == null || usuarioVO.getLogin().isEmpty()) {
			System.out.println("O campo Login é obrigatório");
			resultado = false;
		}
		if(usuarioVO.getSenha() == null || usuarioVO.getSenha().isEmpty()) {
			System.out.println("O campo Senha é obrigatório");
			resultado = false;
		}
		return resultado;
	}
	
	private void consultarUsuario() {
		System.out.println("Consultando usuario");
	}

	private void atualizarUsuario() {
		System.out.println("Atualizando usuario");
	}

	private void excluirUsuario() {
		System.out.println("Excluindo usuario");
	}
}
