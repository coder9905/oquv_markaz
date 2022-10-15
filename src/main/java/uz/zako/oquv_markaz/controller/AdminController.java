package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import uz.zako.oquv_markaz.payload.*;
import uz.zako.oquv_markaz.service.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/admin/v1")
public class AdminController {

    private final EmployeeService employeeService;
    private final SubjcetService subjcetService;
    private final GroupService groupService;
    private final UserService userService;
    private final NbService nbService;
    private final ScheduleService scheduleService;
    private final WeekService weekService;
    private final MoonService moonService;
    private final DiscountService discountService;
    private final OutputService outputService;
    private final PaymentService paymentService;
    private final SalaryService salaryService;


    @PostMapping("/save/subject/{hashId}")
    public ResponseEntity<?> saveSubject(@RequestBody SubjectPayload payload, @PathVariable("hashId") String hashId) {
        return subjcetService.saveSubjcet(payload);
    }

    @PutMapping("/edit/subject/{hashId}")
    public ResponseEntity<?> editSubject(@PathVariable("hashId") String id, @RequestBody SubjectPayload payload) {
        return subjcetService.editSubject(payload);
    }

    @DeleteMapping("/delete/subject/{subjectId}")
    public ResponseEntity<?> deleteSubjectId(@PathVariable("subjectId") Long id) {
        return subjcetService.deleteSubjectId(id);
    }

    @GetMapping("/getOne/subject/{subjectId}")
    public ResponseEntity<?> getOneSubject(@PathVariable("subjectId") Long id) {
        return subjcetService.getOne(id);
    }

    @GetMapping("/getCenterBranches/subject/{centerBranchesId}")
    public ResponseEntity<?> getAllSubjectCenterBranchesId(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size,@PathVariable("centerBranchesId") Long id) {
        return subjcetService.getSubjectCenterBranchesId(page,size,id);
    }

    @GetMapping("/all/subject")
    public ResponseEntity<?> getAllSubject(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size) {
        return subjcetService.getAllSubject(page, size);
    }

    @PostMapping("/save/group/{hashId}")
    public ResponseEntity<?> saveGroup(@PathVariable("hashId") String id, @RequestBody GroupPayload payload) {
        return groupService.save(id, payload);
    }

    @PutMapping("/edit/group/{hashId}")
    public ResponseEntity<?> editGroup(@PathVariable("hashId") String id, @RequestBody GroupPayload payload) {
        return groupService.editGroups(payload, id);
    }

    @GetMapping("/getOne/group/{groupId}")
    public ResponseEntity<?> getOneGroup(@PathVariable("groupId") Long id) {
        return groupService.getOne(id);
    }

    @GetMapping("/getSubjectId/group/{subjectId}")
    public ResponseEntity<?> getAllGroupSubjectId(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size,@PathVariable("subjectId") Long id) {
        return groupService.getGroupsSubjectId(page,size,id);
    }

    @GetMapping("/getEmployeId/group/{employeId}")
    public ResponseEntity<?> getGroupEmployeId(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size,@PathVariable("employeId") Long id) {
        return groupService.getGroupsEmployesId(page,size,id);
    }

    @GetMapping("/all/group")
    public ResponseEntity<?> getAllGroup(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size) {
        return groupService.getAllGroups(page,size);
    }

    @GetMapping("/getOne/user{userId}")
    public ResponseEntity<?> getOneUser(@PathVariable("userId") Long id) {
        return userService.getOne(id);
    }

    @GetMapping("/getUserGroupId/user{groupId}")
    public ResponseEntity<?> getUserGroupId(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size,@PathVariable("groupId") Long id) {
        return userService.getUserGroupId(page,size,id);
    }

    @GetMapping("/getUsercenterBranchesId/user{centerBranchesId}")
    public ResponseEntity<?> getUsercenterBranchesId(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size,@PathVariable("centerBranchesId") Long id) {
        return userService.getUsercenterBranchesId(page,size,id);
    }

    @GetMapping("/getAllUsercenterBranchesId/user{centerBranchesId}")
    public ResponseEntity<?> getAllUsercenterBranchesId(@PathVariable("centerBranchesId") Long id) {
        return userService.getUsercenterBranchesAllId(id);
    }

    @PostMapping("/save/nb")
    public ResponseEntity<?> saveNb(@RequestBody NbPayload payload) {
        return nbService.save(payload);
    }

    @GetMapping("/getUserId/nb/{userId}")
    public ResponseEntity<?> getNb(@PathVariable("userId") Long id) {
        return nbService.getOne(id);
    }

    @PostMapping("/save/schedule/{teacherId}")
    public ResponseEntity<?> saveSchedule(@PathVariable("teacherId") Long id, @RequestBody SchedulePayload schedulePayload){
        return scheduleService.save(schedulePayload,id);
    }

    @GetMapping("/getTeacherId/schedule/{teacherId}")
    public ResponseEntity<?> getScheduleTeacherId(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size,@PathVariable("teacherId") Long id){
        return scheduleService.getScheduleTeacherId(page,size,id);
    }

    @GetMapping("/getScheduleId/schedule/{weekId}")
    public ResponseEntity<?> getScheduleWeekId(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size,@PathVariable("weekId") Long id){
        return scheduleService.getScheduleWeekId(page,size,id);
    }

    @GetMapping("/all/schedule")
    public ResponseEntity<?> getAllSchedule(){
        return scheduleService.getAllSchedule();
    }

    @PostMapping("/save/week")
    public ResponseEntity<?> saveWeek(@RequestBody WeekPayload payload){
        return weekService.saveWeek(payload);
    }

    @PutMapping("/edit/week/{weekId}")
    public ResponseEntity<?> editWeek(@PathVariable("weekId") Long id,@RequestBody WeekPayload payload){
        return weekService.editWeek(id,payload);
    }

    @GetMapping("/all/week")
    public ResponseEntity<?> getAllWeek(){
        return weekService.getAllWeek();
    }

    @PostMapping("/save/moon")
    public ResponseEntity<?> saveMoon(@RequestBody MoonPayload payload){
        return moonService.saveMoon(payload);
    }

    @PutMapping("/edit/moon/{moonId}")
    public ResponseEntity<?> editMoon(@PathVariable("moonId") Long id,@RequestBody MoonPayload payload){
        return moonService.editMoon(id,payload);
    }

    @GetMapping("/all/moon")
    public ResponseEntity<?> getAllMoon(){
        return moonService.getAllMoon();
    }

    @PostMapping("/save/discount/{discountId}")
    public ResponseEntity<?> saveDiscount(@PathVariable("discountId") Long id, @RequestBody DiscountPayload payload){
        return discountService.saveDiscount(id,payload);
    }

    @PutMapping("/edit/discount/{discountId}")
    public ResponseEntity<?> editDiscount(@PathVariable("discountId") Long id, @RequestBody DiscountPayload payload){
        return discountService.editDiscount(id,payload);
    }

    @GetMapping("/get/discount/{discountId}")
    public ResponseEntity<?> editDiscount(@PathVariable("discountId") Long id){
        return discountService.getDiscountId(id);
    }

    @GetMapping("/all/discount")
    public ResponseEntity<?> allDiscount(){
        return discountService.getAllDiscount();
    }

    @GetMapping("/getGroupId/discount/{groupId}")
    public ResponseEntity<?> getDiscountGroupId(@PathVariable("groupId") Long id){
        return discountService.getDiscountGroupId(id);
    }

    @DeleteMapping("/deleteDiscountId/discount/{discountId}")
    public ResponseEntity<?> deleteDiscountId(@PathVariable("discountId") Long id){
        return discountService.getDiscountGroupId(id);
    }

    @PostMapping("/save/output")
    public ResponseEntity<?> saveOutput(@RequestBody OutputPayload payload){
        return outputService.saveOutput(payload);
    }

    @PutMapping("/edit/output/{outputId}")
    public ResponseEntity<?> saveOutput(@PathVariable("outputId") Long id, @RequestBody OutputPayload payload){
        return outputService.editDiscount(id,payload);
    }

    @DeleteMapping("/delete/output/{outputId}")
    public ResponseEntity<?> deleteOutput(@PathVariable("outputId") Long id){
        return outputService.deleteOutputuserId(id);
    }

    @GetMapping("/all/output")
    public ResponseEntity<?> getAllOutput(){
        return outputService.getAllOutput();
    }


    @PostMapping("/save/payment")
    public ResponseEntity<?> savePAyment(@RequestBody PaymentPayload payload){
        return paymentService.savePayment(payload);
    }

    @PutMapping("/edit/payment/{paymentId}")
    public ResponseEntity<?> editPayment(@PathVariable("paymentId") Long id,@RequestBody PaymentPayload payload){
        return paymentService.editPayment(id,payload);
    }

    @DeleteMapping("/delete/payment/{paymentId}")
    public ResponseEntity<?> deletePaymentId(@PathVariable("paymentId") Long id){
        return paymentService.deletePaymentId(id);
    }

    @GetMapping("/all/payment")
    public ResponseEntity<?> getAllPayment(){
        return paymentService.getAllPayment();
    }

    @GetMapping("/getUserId/payment/{userId}")
    public ResponseEntity<?> getPaymentUserId(@PathVariable("userId") Long id){
        return paymentService.getPaymentUserId(id);
    }

    @GetMapping("/getGroupId/payment/{groupId}")
    public ResponseEntity<?> getPaymentGroupId(@PathVariable("groupId") Long id){
        return paymentService.getPaymentUserId(id);
    }

    @GetMapping("/getMoonId/payment/{moonId}")
    public ResponseEntity<?> getPaymentMoonId(@PathVariable("moonId") Long id){
        return paymentService.getPaymentMoonId(id);
    }

    @PostMapping("/save/salary")
    public ResponseEntity<?> saveSalary(@RequestBody SalaryPayload payload){
        return salaryService.saveSalary(payload);
    }

    @PutMapping("/edit/salary/{salaryId}")
    public ResponseEntity<?> editSalaryId(@PathVariable("salaryId") Long id, @RequestBody SalaryPayload payload){
        return salaryService.editSalary(id, payload);
    }

    @DeleteMapping("/delete/salary/{salaryId}")
    public ResponseEntity<?> deleteSalaryId(@PathVariable("salaryId") Long id){
        return salaryService.deleteSalaryId(id);
    }

    @GetMapping("/all/salary")
    public ResponseEntity<?> getAllSalary(){
        return salaryService.getAllSalary();
    }

    @GetMapping("/getMoonId/salary/{moonId}")
    public ResponseEntity<?> getSalaryMoonId(@PathVariable("moonId") Long id){
        return salaryService.getSalaryMoonId(id);
    }


}

