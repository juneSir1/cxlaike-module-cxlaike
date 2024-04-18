package net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.material;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.util.Base64;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.hzxzkj.cxlaike.framework.common.exception.ErrorCode;
import net.hzxzkj.cxlaike.framework.common.pojo.CommonResult;
import net.hzxzkj.cxlaike.framework.common.pojo.PageResult;
import net.hzxzkj.cxlaike.framework.common.util.http.Http2Utils;
import net.hzxzkj.cxlaike.framework.common.util.servlet.ServletUtils;
import net.hzxzkj.cxlaike.framework.idempotent.core.annotation.Idempotent;
import net.hzxzkj.cxlaike.framework.security.core.annotations.PreAuthenticated;
import net.hzxzkj.cxlaike.framework.security.core.util.SecurityFrameworkUtils;
import net.hzxzkj.cxlaike.framework.tenant.core.aop.TenantIgnore;
import net.hzxzkj.cxlaike.framework.tenant.core.context.TenantContextHolder;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.material.vo.CreateFolderVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.material.vo.FileUploadReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.material.vo.FileUploadTemporarilyReqVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.material.vo.UpdateFileVO;
import net.hzxzkj.cxlaike.module.cxlaike.controller.merchant.matrixtask.vo.MatrixTaskPushVideoRespVO;
import net.hzxzkj.cxlaike.module.cxlaike.service.aliyun.AliyunStsCallbackService;
import net.hzxzkj.cxlaike.module.cxlaike.service.material.MaterialService;
import net.hzxzkj.cxlaike.module.infra.api.file.dto.*;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

import static net.hzxzkj.cxlaike.framework.common.exception.util.ServiceExceptionUtil.exception;
import static net.hzxzkj.cxlaike.framework.common.pojo.CommonResult.success;

@Tag(name = "商户后台 - 素材")
@RestController
@RequestMapping("/cxlaike/material")
@Validated
@Slf4j
public class MaterialController {

    @Resource
    private MaterialService materialService;

    @Resource
    private AliyunStsCallbackService aliyunStsCallbackService;

    private static final String REGEX = "^[A-Za-z0-9_\\-+\\s.\\p{IsHan}~`!@#$%^&*()\\-+=|\\\\{}\\[\\]:;\"'<>,.?/]+$";



    @SneakyThrows
    @GetMapping("/uploadCallbackGet")
    @Operation(summary = "前端文件上传后回调")
    @Idempotent(message = "请勿重复提交")
    public void uploadCallbackGet(HttpServletRequest request, HttpServletResponse response) {
        log.info("前端文件上传后回调Get  requestBody:{}", ServletUtils.getBody(request));
        log.info("前端文件上传后回调Get  request:{}， response:{}", JSONUtil.toJsonStr(request), JSONUtil.toJsonStr(response));
    }

    @SneakyThrows
    @PostMapping("/uploadCallbackPost")
    @Operation(summary = "前端文件上传后回调")
    @Idempotent(message = "请勿重复提交")
    public CommonResult<MaterialRespVO> uploadCallbackPost(HttpServletRequest request, HttpServletResponse response) {
        log.info("前端文件上传后回调Get  requestBody:{}", ServletUtils.getBody(request));
        log.info("前端文件上传后回调Get  request:{}， response:{}", JSONUtil.toJsonStr(request), JSONUtil.toJsonStr(response));
        return success(aliyunStsCallbackService.doPost(request, response));

    }

    @GetMapping("/getStsToken")
    @Operation(summary = "前端文件上传后回调")
    public String uploadCallbackPost() {
//        log.error("前端文件上传后回调Get  requestBody:{}", ServletUtils.getBody(request));
//        log.error("前端文件上传后回调Get  request:{}， response:{}", JSONUtil.toJsonStr(request), JSONUtil.toJsonStr(response));
        String s = "";
        try {
            s = Http2Utils.doGet01("http://oss.cxlaike.com:8080/cxlaike-sub/file/getStsToken", Maps.newHashMap());

        }catch (Exception e){
            e.printStackTrace();
        }
        return s;

    }

    /*@SneakyThrows
    @PostMapping("/uploadCallbackPost")
    @Operation(summary = "前端文件上传后回调")
    @Idempotent(message = "请勿重复提交")
    @ResponseBody
    public String uploadCallbackPost(HttpServletRequest request, HttpServletResponse response) {
        String body = ServletUtils.getBody(request);
        try {
            log.error("前端文件上传后回调Post  requestBody:{}", body);
            String autorizationInput = new String(request.getHeader("Authorization"));
            log.error("前端文件上传后回调Post  autorizationInput:{}", autorizationInput);
            String pubKeyInput = request.getHeader("x-oss-pub-key-url");
            log.error("前端文件上传后回调Post  pubKeyInput:{}", pubKeyInput);
            byte[] authorization = Base64.base64ToByteArray(autorizationInput);
            byte[] pubKey = Base64.base64ToByteArray(pubKeyInput);
            String pubKeyAddr = new String(pubKey);
            log.error("前端文件上传后回调Post  pubKeyAddr:{}", pubKeyAddr);
            if (!pubKeyAddr.startsWith("https://gosspublic.alicdn.com/")
                    && !pubKeyAddr.startsWith("https://gosspublic.alicdn.com/")) {
                System.out.println("pub key addr must be oss addrss");
            }
            String retString = Http2Utils.doGet01(pubKeyAddr, Maps.newHashMap());
            log.error("前端文件上传后回调Post  retString:{}", retString);
            retString = retString.replace("-----BEGIN PUBLIC KEY-----", "");
            retString = retString.replace("-----END PUBLIC KEY-----", "");
            String queryString = request.getQueryString();
            log.error("前端文件上传后回调Post  queryString:{}", queryString);

            String uri = request.getRequestURI();
            log.error("前端文件上传后回调Post  uri:{}", uri);
            String decodeUri = java.net.URLDecoder.decode(uri, "UTF-8");
            log.error("前端文件上传后回调Post  decodeUri:{}", decodeUri);
            String authStr = decodeUri;
            if (queryString != null && !queryString.equals("")) {
                authStr += "?" + queryString;
            }
//            authStr += "\n" + ossCallbackBody;
        }catch (Exception e){
            e.printStackTrace();
        }
        materialService.createMaterialForSts(fileName, path,
                IoUtil.readBytes(file.getInputStream()), uploadReqVO.getMaterialType(),
                TenantContextHolder.getTenantId())
        log.error("前端文件上传后回调Post  request:{}， response:{}", JSONUtil.toJsonStr(request), JSONUtil.toJsonStr(response));
        return "{\"Status\":\"OK\",\"Message\":"+ body+" }";
    }*/

    public static void main(String[] args) {
//        String retString = Http2Utils.doGet01(pubKeyAddr, Maps.newHashMap());
//        retString = retString.replace("-----BEGIN PUBLIC KEY-----", "");
//        retString = retString.replace("-----END PUBLIC KEY-----", "");
//        String queryString = request.getQueryString();
//        String uri = request.getRequestURI();
//        String decodeUri = java.net.URLDecoder.decode(uri, "UTF-8");
//        String authStr = decodeUri;
//        if (queryString != null && !queryString.equals("")) {
//            authStr += "?" + queryString;
//        }
    }
    @PostMapping("/createFolder")
    @Operation(summary = "新建文件夹")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<Boolean> createFolder(@Valid @RequestBody CreateFolderVO createReqVO) {
        createReqVO.setUserId(TenantContextHolder.getTenantId());
        materialService.createFolder(createReqVO);
        return success(true);
    }


    @PostMapping("/upload")
    @Operation(summary = "上传文件")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<String> uploadFile(FileUploadReqVO uploadReqVO) throws Exception {
        MultipartFile file = uploadReqVO.getFile();
        String path = uploadReqVO.getPath();
        String fileName = this.checkName(file.getOriginalFilename());
        return success(materialService.createFile(fileName, path,
                IoUtil.readBytes(file.getInputStream()), uploadReqVO.getMaterialType(),
                TenantContextHolder.getTenantId()));
    }

    @PostMapping("/uploadMaterial")
    @Operation(summary = "上传文件(返回文件对象)")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<MaterialRespVO> uploadMaterial(FileUploadReqVO uploadReqVO) throws Exception {
        MultipartFile file = uploadReqVO.getFile();
        String path = uploadReqVO.getPath();
        String fileName = this.checkName(file.getOriginalFilename());
        return success(materialService.createMaterial(fileName, path,
                IoUtil.readBytes(file.getInputStream()), uploadReqVO.getMaterialType(),
                TenantContextHolder.getTenantId()));
    }

    @PostMapping("/transfer")
    @Operation(summary = "移动文件")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<Boolean> transfer(@Valid @RequestBody UpdateFileVO transferFileVO) throws Exception {
        materialService.transfer(transferFileVO.getId(), transferFileVO.getPath()
                ,TenantContextHolder.getTenantId());
        return success(true);
    }

    @PostMapping("/rename")
    @Operation(summary = "重命名")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<Boolean> rename(@Valid @RequestBody UpdateFileVO transferFileVO) throws Exception {
        String fileName = this.checkName(transferFileVO.getName());
        materialService.rename(transferFileVO.getId(), transferFileVO.getPath(), fileName
                ,TenantContextHolder.getTenantId());
        return success(true);
    }

    @PostMapping("/deleteFile")
    @Operation(summary = "删除")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<Boolean> deleteFile(@RequestParam("id") Long id) throws Exception {
        materialService.deleteFile(id);
        return success(true);
    }

    @GetMapping("/page")
    @Operation(summary = "获得文件分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<PageResult<MaterialRespVO>> getFilePage(@Valid FileMaterialPageReqVO pageVO) {
        return success(materialService.getFilePage(pageVO));
    }

    @GetMapping("/getListAll")
    @Operation(summary = "获得文件列表(所有)")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<List<MaterialRespVO>> getList(@Valid FileListReqVO pageVO) {
        return success(materialService.getMaterialList(pageVO));
    }

    @GetMapping("/list")
    @Operation(summary = "获得文件列表(根据路径)")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<List<MaterialRespVO>> getListByPath(@Valid FileListReqVO pageVO) {
        return success(materialService.getMaterialListByPath(pageVO));
    }

    @GetMapping("/list1")
    @Operation(summary = "获得文件列表分页(根据路径)")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<PageResult<MaterialRespVO>> getListByPath1(@Valid FileMaterialPageReqVO pageVO) {
        return success(materialService.getMaterialListByPath1(pageVO));
    }

    @GetMapping("/getFolderList")
    @Operation(summary = "获得当前目录下的文件夹列表")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<List<FolderRespVO>> getFolderList(@RequestParam("path") String path,
                                                          @RequestParam("materialType")Integer materialType) {
        return success(materialService.getFolderList(TenantContextHolder.getTenantId(),path,materialType));
    }

    @GetMapping("/sys_page")
    @Operation(summary = "获得系统文件分页")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<PageResult<MaterialRespVO>> getFilePage(@Valid @ParameterObject FileSysPageReqVO pageVO) {
        return success(materialService.getSysFilePage(pageVO));
    }

    @GetMapping("/sys_list")
    @Operation(summary = "获得系统文件列表")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<List<MaterialRespVO>> getList(@RequestParam("type") Integer type, @RequestParam(value = "name", required = false) String name) {
        return success(materialService.getSysMaterialList(type, name));
    }

    @PostMapping("/createTemporarilyFile")
    @Operation(summary = "上传临时文件(返回文件对象)")
    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    public CommonResult<MaterialRespVO> createTemporarilyFile(FileUploadTemporarilyReqVO uploadReqVO) throws Exception {
        MultipartFile file = uploadReqVO.getFile();
        String fileName = this.checkName(file.getOriginalFilename());
        return success(materialService.createTemporarilyFile(fileName,
                IoUtil.readBytes(file.getInputStream()),
                TenantContextHolder.getTenantId(),uploadReqVO.getMaterialType()));
    }

    @PostMapping("/createSysMaterialFile")
    @Operation(summary = "上传素材文件")
//    @PreAuthorize("@mss.hasPermission('cxlaike:material')")
    @TenantIgnore
    public CommonResult<Boolean> createSysMaterialFile(@RequestBody DyMusicReqVO reqVO) {
        materialService.createSysMaterialFile(reqVO.getData().getSongs(), reqVO.getMaterialType());
        return success(true);
    }

    private String checkName(String name){
        String pattern = REGEX;
        boolean matches = name.matches(pattern);
        if (!matches) {
           throw exception(new ErrorCode(400,"文件夹名或文件名不能包含特殊字符"));
        }
        if(name.length() > 64){
            return name.substring(0, 64);
        }

        return name;
    }

}
