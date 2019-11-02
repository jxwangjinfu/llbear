package com.junfeng.platform.dc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junfeng.platform.dc.api.result.UndoneCommands;
import com.junfeng.platform.dc.api.vo.CreateCommandVo;
import com.junfeng.platform.dc.entity.Command;
import com.junfeng.platform.dc.service.CommandService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 指令
 *
 * @author fuh
 * @date 2019-09-18 16:09:31
 */
@Api(tags = {"指令"})
@RestController
@AllArgsConstructor
@RequestMapping("/command")
public class CommandController {

    private final CommandService commandService;

    /**
     * 简单分页查询
     * 
     * @param page
     *            分页对象
     * @param command
     *            指令
     * @return
     */
    @ApiOperation(value = "getCommandPage", notes = "分页获取指令")
    @GetMapping("/page")
    public R<IPage<Command>> getCommandPage(Page<Command> page, Command command) {
        return R.ok(commandService.getCommandPage(page, command));
    }

    /**
     * 通过id查询单条记录
     * 
     * @param id
     * @return R
     */
    @ApiOperation(value = "getById", notes = "获取指令")
    @GetMapping("/{id}")
    public R<Command> getById(@PathVariable("id") Long id) {
        return R.ok(commandService.getById(id));
    }

    /**
     * 新增记录
     * 
     * @param command
     * @return R
     */
    @SysLog("新增指令")
    @ApiOperation(value = "save", notes = "新增指令")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('dc_command_add')")
    public R<Boolean> save(@RequestBody Command command) {
        return R.ok(commandService.save(command));
    }

    /**
     * 修改记录
     * 
     * @param command
     * @return R
     */
    @SysLog("修改指令")
    @ApiOperation(value = "update", notes = "修改指令")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('dc_command_edit')")
    public R<Boolean> update(@RequestBody Command command) {
        return R.ok(commandService.updateById(command));
    }

    /**
     * 通过id删除一条记录
     * 
     * @param id
     * @return R
     */
    @SysLog("删除指令")
    @ApiOperation(value = "removeById", notes = "删除指令")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('dc_command_del')")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(commandService.removeById(id));
    }

    /**
     * 发送指令
     * 
     * @param sendCommandVo
     * @return
     * @author:fuh
     * @createTime:2019年9月18日 下午4:47:57
     */
    @ApiOperation(value = "send", notes = "发送指令")
    @PostMapping("/send")
    @PreAuthorize("@pms.hasPermission('dc_command_add')")
    public R<Boolean> send(@RequestBody CreateCommandVo sendCommandVo) {

        boolean sendCommand = commandService.sendCommand(sendCommandVo);
        return R.ok(sendCommand);
    }

    /**
     * 获取指令相关的设备列表
     * 
     * @param commandId
     * @param type
     * @return
     * @author:fuh
     * @createTime:2019年9月18日 下午4:48:25
     */
    @GetMapping("/{commandId}/device_list")
    public R<?> getShopList(@PathVariable("commandId") Long commandId, Integer type) {

        // List<Shop> listPage = shopCommandService.findShopList(commandId,type);

        return R.ok();
    }

    /**
     * 撤销单个设备的指令
     * 
     * @param commandId
     * @param deviceId
     * @return
     * @author:fuh
     * @createTime:2019年9月18日 下午4:48:58
     */
    @PutMapping("/cancel/{commandId}/{deviceId}")
    @PreAuthorize("@pms.hasPermission('dc_command_edit')")
    public R<Integer> cancel(@PathVariable("commandId") Long commandId, @PathVariable("deviceId") Long deviceId) {

        int result = commandService.cancel(commandId, deviceId);

        return R.ok(result);
    }

    /**
     * 获取待完成指令
     * 
     * @param deviceId
     * @return
     * @author:fuh
     * @createTime:2019年9月18日 下午5:27:40
     */
    @GetMapping("/undone/{deviceId}")
    public R<UndoneCommands> getUndone(@PathVariable Long deviceId) {

        return R.ok(commandService.getUndoneCommands(deviceId));
    }

    /**
     * 上传完成指令
     * 
     * @param deviceId
     * @param commandId
     * @return
     * @author:fuh
     * @createTime:2019年9月18日 下午5:28:01
     */
    @PutMapping("/done/{deviceId}/{commandId}")
    public R<Boolean> done(@PathVariable("deviceId") Long deviceId, @PathVariable("commandId") Long commandId) {

        return R.ok(commandService.done(deviceId, commandId));
    }
}
