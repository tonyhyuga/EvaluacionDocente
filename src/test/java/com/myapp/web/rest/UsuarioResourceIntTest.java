//package com.myapp.web.rest;
//
//import com.myapp.CampoApp;
//
//import com.myapp.domain.Usuario;
//import com.myapp.repository.UsuarioRepository;
//import com.myapp.service.UsuarioService;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import static org.hamcrest.Matchers.hasItem;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
///**
// * Test class for the UsuarioResource REST controller.
// *
// * @see UsuarioResource
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = CampoApp.class)
//public class UsuarioResourceIntTest {
//
//    private static final String DEFAULT_LOGIN = "AAAAA";
//    private static final String UPDATED_LOGIN = "BBBBB";
//    private static final String DEFAULT_PASSWORD = "AAAAA";
//    private static final String UPDATED_PASSWORD = "BBBBB";
//    private static final String DEFAULT_ACTIVO = "AAAAA";
//    private static final String UPDATED_ACTIVO = "BBBBB";
//
//    @Inject
//    private UsuarioRepository usuarioRepository;
//
//    @Inject
//    private UsuarioService usuarioService;
//
//    @Inject
//    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
//
//    @Inject
//    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
//
//    @Inject
//    private EntityManager em;
//
//    private MockMvc restUsuarioMockMvc;
//
//    private Usuario usuario;
//
//    @PostConstruct
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        UsuarioResource usuarioResource = new UsuarioResource();
//        ReflectionTestUtils.setField(usuarioResource, "usuarioService", usuarioService);
//        this.restUsuarioMockMvc = MockMvcBuilders.standaloneSetup(usuarioResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setMessageConverters(jacksonMessageConverter).build();
//    }
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Usuario createEntity(EntityManager em) {
//        Usuario usuario = new Usuario()
//                .login(DEFAULT_LOGIN)
//                .password(DEFAULT_PASSWORD)
//                .activo(DEFAULT_ACTIVO);
//        return usuario;
//    }
//
//    @Before
//    public void initTest() {
//        usuario = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createUsuario() throws Exception {
//        int databaseSizeBeforeCreate = usuarioRepository.findAll().size();
//
//        // Create the Usuario
//
//        restUsuarioMockMvc.perform(post("/api/usuarios")
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(usuario)))
//                .andExpect(status().isCreated());
//
//        // Validate the Usuario in the database
//        List<Usuario> usuarios = usuarioRepository.findAll();
//        assertThat(usuarios).hasSize(databaseSizeBeforeCreate + 1);
//        Usuario testUsuario = usuarios.get(usuarios.size() - 1);
//        assertThat(testUsuario.getLogin()).isEqualTo(DEFAULT_LOGIN);
//        assertThat(testUsuario.getPassword()).isEqualTo(DEFAULT_PASSWORD);
//        assertThat(testUsuario.getActivo()).isEqualTo(DEFAULT_ACTIVO);
//    }
//
//    @Test
//    @Transactional
//    public void getAllUsuarios() throws Exception {
//        // Initialize the database
//        usuarioRepository.saveAndFlush(usuario);
//
//        // Get all the usuarios
//        restUsuarioMockMvc.perform(get("/api/usuarios?sort=id,desc"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$.[*].id").value(hasItem(usuario.getId().intValue())))
//                .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN.toString())))
//                .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
//                .andExpect(jsonPath("$.[*].activo").value(hasItem(DEFAULT_ACTIVO.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getUsuario() throws Exception {
//        // Initialize the database
//        usuarioRepository.saveAndFlush(usuario);
//
//        // Get the usuario
//        restUsuarioMockMvc.perform(get("/api/usuarios/{id}", usuario.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(usuario.getId().intValue()))
//            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN.toString()))
//            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
//            .andExpect(jsonPath("$.activo").value(DEFAULT_ACTIVO.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getNonExistingUsuario() throws Exception {
//        // Get the usuario
//        restUsuarioMockMvc.perform(get("/api/usuarios/{id}", Long.MAX_VALUE))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateUsuario() throws Exception {
//        // Initialize the database
//        usuarioService.save(usuario);
//
//        int databaseSizeBeforeUpdate = usuarioRepository.findAll().size();
//
//        // Update the usuario
//        Usuario updatedUsuario = usuarioRepository.findOne(usuario.getId());
//        updatedUsuario
//                .login(UPDATED_LOGIN)
//                .password(UPDATED_PASSWORD)
//                .activo(UPDATED_ACTIVO);
//
//        restUsuarioMockMvc.perform(put("/api/usuarios")
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(updatedUsuario)))
//                .andExpect(status().isOk());
//
//        // Validate the Usuario in the database
//        List<Usuario> usuarios = usuarioRepository.findAll();
//        assertThat(usuarios).hasSize(databaseSizeBeforeUpdate);
//        Usuario testUsuario = usuarios.get(usuarios.size() - 1);
//        assertThat(testUsuario.getLogin()).isEqualTo(UPDATED_LOGIN);
//        assertThat(testUsuario.getPassword()).isEqualTo(UPDATED_PASSWORD);
//        assertThat(testUsuario.getActivo()).isEqualTo(UPDATED_ACTIVO);
//    }
//
//    @Test
//    @Transactional
//    public void deleteUsuario() throws Exception {
//        // Initialize the database
//        usuarioService.save(usuario);
//
//        int databaseSizeBeforeDelete = usuarioRepository.findAll().size();
//
//        // Get the usuario
//        restUsuarioMockMvc.perform(delete("/api/usuarios/{id}", usuario.getId())
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<Usuario> usuarios = usuarioRepository.findAll();
//        assertThat(usuarios).hasSize(databaseSizeBeforeDelete - 1);
//    }
//}
